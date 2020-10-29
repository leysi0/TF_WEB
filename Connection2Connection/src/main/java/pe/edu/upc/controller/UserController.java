package pe.edu.upc.controller;

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

import pe.edu.upc.model.Role;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IRoleService rService;
	

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "muro";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new User());
		return "index3";
	}
	
	@RequestMapping("/irRegistrarAdmin")
	public String irRegistrarAdmin(Model model) {
		model.addAttribute("user", new User());
		return "Registro_Admin";
	}
	
	@RequestMapping("/irRegistrarEmpresario")
	public String irRegistrarEmpresario(Model model) {
		model.addAttribute("user", new User());
		return "index4";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "index3";
		}
		else {
			Role Ra=rService.search(1);
			if(Ra == null){
				Role rool = new Role();
				rool.setIdRole(1);
				rool.setNameRole("Estudiante");
				rService.insertar(rool);
				objUser.setRole(rool);
			}
			else {
				objUser.setRole(Ra);
			}
			Date requestday = new Date();
			objUser.setDate(requestday);
			boolean flag = uService.insertar(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/user/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/registrarAdmin")
	public String registrarAdmin(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			Role Ra=rService.search(2);
			if(Ra == null){
				Role rool = new Role();
				rool.setIdRole(2);
				rool.setNameRole("Administrador");
				rService.insertar(rool);
				objUser.setRole(rool);
			}
			else {
				objUser.setRole(Ra);
			}
			Date requestday = new Date();
			objUser.setDate(requestday);
			boolean flag = uService.insertar(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/user/irRegistrarAdmin";
			}
		}
	}
	
	@RequestMapping("/registrarEmpresario")
	public String registrarEmpresario(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			Role Ra=rService.search(3);
			if(Ra == null){
				Role rool = new Role();
				rool.setIdRole(3);
				rool.setNameRole("Empresario");
				rService.insertar(rool);
				objUser.setRole(rool);
			}
			else {
				objUser.setRole(Ra);
			}
			Date requestday = new Date();
			objUser.setDate(requestday);
			boolean flag = uService.insertar(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/user/irRegistrarEmpresario";
			}
		}
	}
	

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<User> user = uService.listarId(id);
		
		if (user == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/user/listar";
		}
		else {
			model.addAttribute("user", user.get());
			model.addAttribute("listRole", rService.listar());
			return "Modificar_perfil_Admin";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) throws ParseException
	{
			
			Date requestday = new Date();
			objUser.setDate(requestday);
			boolean flag = uService.modificar(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "Modificar_perfil_Admin";
			}
		}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				uService.eliminar(id);
				model.put("listaUsers", uService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaUsers", uService.listar());
		}
		return "listUser";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsers", uService.listar());
		return "listUser";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute User user) 
	throws ParseException
	{
		uService.listarId(user.getIdUser());
		return "listUser";
	}	
	
	@RequestMapping("/busqueda")
	public String buscar(Map<String, Object> model, @ModelAttribute User user ) throws ParseException {
		List<User> listaUsers;
		user.setNameUser(user.getNameUser());
		listaUsers = uService.buscarNombre(user.getNameUser());
		
		if (listaUsers.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaUsers", listaUsers);				
		return "busqueda";
	}
	
	@RequestMapping("/irBusqueda")
	public String irBuscar(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
}
