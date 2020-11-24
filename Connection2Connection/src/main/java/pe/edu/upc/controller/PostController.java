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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Comentario;
import pe.edu.upc.model.Post;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IComentarioService;
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
	
	@Autowired
	private IRoleService rService;
	
	@Autowired
	private IComentarioService cService;
	
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
	        objPost.setUser(cuenta2); 
	        model.addAttribute("idPost", objPost.getIdPost());
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
        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
    	model.put("user", new Users());
		model.put("post", new Post());
        model.put("cuenta", cuenta2);
        model.put("idCuenta", cuenta2.getIdUser());
		model.put("listPosts", pService.listar());
		model.put("comentario", new Comentario());
	
		return "listPost";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir){
		
		Optional<Users> user = uService.listarId(id);
		
		if (user == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
			return "redirect:/post/listar";
		}
		else {
			model.addAttribute("user", user.get());
			model.addAttribute("listRole", rService.listar());
			return "Modificar_Usuario";
		}
	}
	
	@RequestMapping("/guardar")
	public String guardar(@ModelAttribute @Valid Users objUser, BindingResult binRes, Model model) throws ParseException
	{
			Date requestday = new Date();
			objUser.setDate(requestday);
			boolean flag = uService.modificar(objUser);
			if (flag) {
				return "redirect:/post/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "Modificar_Usuario";
			}
		}
	
	@RequestMapping("/registrarcomentario/{id}")
	public String registrarcomentario(@PathVariable int id,@ModelAttribute @Valid Comentario objComentario, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios",uService.listar());
			model.addAttribute("listPosts", pService.listar());
			return "post";
		}
		else {
			Date requestday=new Date();
			objComentario.setDate(requestday);
			
			Authentication auth = SecurityContextHolder
	                .getContext()
	                .getAuthentication();
	        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	        cuenta2 = this.uService.getAccount(userDetail.getUsername()); //username=correo
	        
			objComentario.setUser(cuenta2); 
			Optional<Post> posteo = pService.listarid(id);
			objComentario.setPost(posteo.get()); 
			boolean flag = cService.insertar(objComentario);
			if (flag) {
				
				String cadena = "redirect:/post/postdetalle/"+id;
				return cadena;
			}
			else {
				model.addAttribute("mensaje", "Sucedio un error");
				return "redirect:/post/irRegistrar";
			}
		}
		}
	
	@GetMapping("/postdetalle/{id}")
	public String postdetalle(@PathVariable int id, Model model) 
	throws ParseException
	{
	
			Optional<Post> posteo = pService.listarid(id);
			model.addAttribute("user", new Users());
			model.addAttribute("post", posteo.get());
	        model.addAttribute("cuenta", cuenta2);
	        model.addAttribute("idCuenta", cuenta2.getIdUser());
			model.addAttribute("comentario", new Comentario());
			model.addAttribute("listComentarios", cService.listarComentariosxPublicacion(id));	
			return "detailPost";
		
		}
	
}
