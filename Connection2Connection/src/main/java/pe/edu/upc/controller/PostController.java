package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Post;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IPostService;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IPostService pService;
	
	private Users cuenta2;
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Post objPost, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			model.addAttribute("listPosts", pService.listar());
			return "post";
		}
		else {
			Date requestday=new Date();
			objPost.setDate(requestday);
			Authentication auth = SecurityContextHolder .getContext().getAuthentication();
	        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
	        objPost.setUser(cuenta2);
			boolean flag = pService.insertar(objPost);
			if (flag) {
				return "redirect:/post/listar";
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/post/irRegistrar";
			}
		}
		}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				pService.eliminar(id);
				model.put("listPosts", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listPosts", pService.listar());
		}
		return "listUser";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername());
    	model.put("user", new Users());
		model.put("post", new Post());
        model.put("cuenta", cuenta2.getNameUser());
        model.put("idCuenta", cuenta2.getIdUser());
		model.put("listPosts", pService.listar());
		return "ListPost";
	}
	
}
