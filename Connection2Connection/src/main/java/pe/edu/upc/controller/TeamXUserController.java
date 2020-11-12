/*package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Team;
import pe.edu.upc.model.TeamXUser;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IMessageService;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.ITeamService;
import pe.edu.upc.service.ITeamXUserService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/teamxuser")
public class TeamXUserController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private ITeamXUserService txuserService;
	
	@Autowired
	private ITeamService tService;
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listUser", uService.listar());
		model.addAttribute("user", new Users());
		model.addAttribute("listTeam", tService.listar());
		model.addAttribute("team", new Team());
		
		return "NuevoMensaje";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TeamXUser objTeam, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaGruposDetalles",uService.listar());
			return "team";
		}
		else {
			
			boolean flag = txuserService.insertar(objTeam);
			if (flag) {
				return "redirect:/team/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/team/irRegistrar";
			}
		}
		}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				tService.eliminar(id);
				model.put("listaMensajes", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaMensajes", tService.listar());
		}
		return "listTeam";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaGrupos", tService.listar());
		return "listTeam";
	}
	
}
*/