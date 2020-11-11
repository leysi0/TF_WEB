package pe.edu.upc.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error",required =false)String error, @RequestParam(value="logout",required=false)String logout, 
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal !=null) {
			flash.addFlashAttribute("info","Ya ha iniciado sesion anteriormente");
			return "redirect:/post/listar";
		}
		
		if(error !=null) {
			model.addAttribute("error","Error en el login: Nombre de usuario o contraseña incorrecto");
			return "login";
		}
		
		if(logout !=null) {
			model.addAttribute("Success","Ha cerrado sesión correctamente");
			return "login";
		}
		
		return "login";
	}
}
