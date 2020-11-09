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
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listUser", uService.listar());
		model.addAttribute("user", new Users());
		model.addAttribute("post", new Post());
		return "NewPost";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Post objPost, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			return "post";
		}
		else {
			Date requestday=new Date();
			objPost.setDate(requestday);
			
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
		model.put("listPosts", pService.listar());
		return "ListPost";
	}
	
}
