package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Role;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IRoleService rService;
	
	
	@RequestMapping("/irRegistrarEstudiante")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new Users());
		return "RegistroEstudiante";
	}
	
	@RequestMapping("/irRegistrarAdmin")
	public String irRegistrarAdmin(Model model) {
		model.addAttribute("user", new Users());
		return "Registro_Admin";
	}
	
	@RequestMapping("/irRegistrarEmpresario")
	public String irRegistrarEmpresario(Model model) {
		model.addAttribute("user", new Users());
		return "RegistroEmpresario";
	}
	
	@RequestMapping("/registrarEstudiante")
	public String registrar(@ModelAttribute @Valid Users objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "RegistroEstudiante";
		}
		else {
			Role Ra=rService.search(1);
			objUser.setRole(Ra);
			Date requestday = new Date();
			String bcryptPassword = passwordEncoder.encode(objUser.getContra());
			objUser.setContra(bcryptPassword);
			objUser.setDate(requestday);
			boolean flag = uService.insertar(objUser);
			if (flag) {
				return "redirect:/post/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/user/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/registrarAdmin")
	public String registrarAdmin(@ModelAttribute @Valid Users objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			Role Ra=rService.search(2);
			objUser.setRole(Ra);
			Date requestday = new Date();
			String bcryptPassword = passwordEncoder.encode(objUser.getContra());
			objUser.setContra(bcryptPassword);
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
	public String registrarEmpresario(@ModelAttribute @Valid Users objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			Role Ra=rService.search(3);
			objUser.setRole(Ra);
			Date requestday = new Date();
			String bcryptPassword = passwordEncoder.encode(objUser.getContra());
			objUser.setContra(bcryptPassword);
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
		
		Optional<Users> user = uService.listarId(id);
		
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
	public String guardar(@ModelAttribute @Valid Users objUser, BindingResult binRes, Model model) throws ParseException
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
	public String listar(Map<String, Object> model, @ModelAttribute Users user) 
	throws ParseException
	{
		uService.listarId(user.getIdUser());
		return "listUser";
	}	
	
	@RequestMapping("/busqueda")
	public String buscar(Map<String, Object> model, @ModelAttribute Users user ) throws ParseException {
		List<Users> listaUsers;
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
		model.addAttribute("user", new Users());
		return "user";
	}
	
}
