package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IMessageService;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IMessageService mService;
	
	private Users cuenta2;

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername());
        
		model.addAttribute("listUser", uService.listar());
		model.addAttribute("user", new Users());
		model.addAttribute("message", new Message());
		model.addAttribute("cuenta", cuenta2.getNameUser());
		model.addAttribute("idCuenta", cuenta2.getIdUser());
		return "NuevoMensaje";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Message objMessage, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			return "message";
		}
		else {
			Authentication auth = SecurityContextHolder
	                .getContext()
	                .getAuthentication();
	        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
	        objMessage.setCorreoEmisor(cuenta2.getEmail());
			Date requestday=new Date();
			objMessage.setDate(requestday);
			boolean flag = mService.insertar(objMessage);
			if (flag) {
				return "redirect:/message/listarE";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/message/irRegistrar";
			}
		}
		}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminar(id);
				model.put("listaMensajes", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaMensajes", mService.listar());
		}
		return "listUser";
	}
	
	@RequestMapping("/listarE")
	public String listarE(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
		model.put("listaMensajes", mService.listarEnviados(cuenta2.getEmail()));
		return "listMensaje";
	}
	
	@RequestMapping("/listarB")
	public String listarB(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
		model.put("listaMensajes", mService.listarBandeja(cuenta2.getEmail()));
		return "listMensajeBandeja";
	}
	
}
