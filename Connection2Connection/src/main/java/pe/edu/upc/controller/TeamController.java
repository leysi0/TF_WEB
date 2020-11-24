package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Agenda;
import pe.edu.upc.model.Comentario;
import pe.edu.upc.model.Post;
import pe.edu.upc.model.Team;
import pe.edu.upc.model.TeamXUser;
import pe.edu.upc.model.Users;
import pe.edu.upc.service.IAgendaService;
import pe.edu.upc.service.IPostService;
import pe.edu.upc.service.ITeamService;
import pe.edu.upc.service.ITeamXUserService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private ITeamService tService;
	
	@Autowired
	private IPostService pService;
	
	@Autowired
	private ITeamXUserService txService;

	@Autowired
	private IAgendaService aService;

	private Users cuenta2;
	
	@Secured({"ROLE_EMPRESARIO","ROLE_ADMIN"})
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("team", new Team());
		return "Registrar_Grupo";
	}
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Team objTeam, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaGrupos",uService.listar());
			return "Registrar_Grupo";
		}
		else {
				Date requestday=new Date();
				objTeam.setDate(requestday);
				objTeam.setAmountTeam(5);
				boolean flag = tService.insertar(objTeam);
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
				model.put("listaGrupos", tService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaGrupos", tService.listar());
		}
		return "listTeam";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        cuenta2 = this.uService.getAccount(userDetail.getUsername());
		model.put("listaGrupos", tService.listar());
		model.put("team", new Team());
		model.put("cuenta", cuenta2);
        model.put("idCuenta", cuenta2.getIdUser());
		return "listTeam";
	}
	
	@RequestMapping("/unirsegrupo/{id}")
	public String postdetalle(@PathVariable int id, Model model) 
	throws ParseException
	{
		 	Set<TeamXUser> Integrantes = new HashSet<>();
			Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        	UserDetails  userDetail = (UserDetails) auth.getPrincipal();
        	cuenta2 = this.uService.getAccount(userDetail.getUsername());
			Optional<Team> team = tService.listarid(id);
			Team teamcito = new Team();
			teamcito = team.get();
	        TeamXUser integrante= new TeamXUser();
	        integrante.setTeam(teamcito);
	        integrante.setUsers(cuenta2);
	        int rpta = 0;
	        rpta = tService.validar(cuenta2.getIdUser(),teamcito.getIdTeam());
	        if (rpta==0) {
	        	Integrantes.add(integrante);
		        teamcito.setTeamXUser(Integrantes);
		        if(teamcito.getAmountTeam()-1>0) {
		        teamcito.setAmountTeam(teamcito.getAmountTeam()-1);
		        
		        boolean flag = this.tService.modificar(teamcito);
		        
		        model.addAttribute("mensaje", "Te has unido correctamente");
	        	model.addAttribute("listaGrupos", tService.listar());
	        	model.addAttribute("team", new Team());
	     		model.addAttribute("cuenta", cuenta2);
	            model.addAttribute("idCuenta", cuenta2.getIdUser());
	            return "listTeam";
		        }
		        else {
		        	model.addAttribute("mensaje2", "Capacidad al limite");
		        	model.addAttribute("listaGrupos", tService.listar());
		        	model.addAttribute("team", new Team());
		    		model.addAttribute("cuenta", cuenta2);
		            model.addAttribute("idCuenta", cuenta2.getIdUser());
		        	return "listTeam";
		        }
			}
	        else {
	        	model.addAttribute("mensaje", "");
	        	model.addAttribute("mensaje2", "Ya pertences al Grupo");
	        	model.addAttribute("listaGrupos", tService.listar());
	        	model.addAttribute("team", new Team());
	    		model.addAttribute("cuenta", cuenta2);
	            model.addAttribute("idCuenta", cuenta2.getIdUser());
	        	return "listTeam";
	        	
	        }
	       
		}
	@GetMapping("/detailgrupo/{id}")
	public String grupos(@PathVariable int id, Model model) 
	throws ParseException
	{
		Optional<Team> team = tService.listarid(id);
		Team teamcito = new Team();
		teamcito = team.get();
		
		 int rpta = 0;
	        rpta = tService.validar(cuenta2.getIdUser(),teamcito.getIdTeam());
	        if(rpta==1) {
	        model.addAttribute("cuenta", cuenta2);
	        model.addAttribute("idCuenta", cuenta2.getIdUser());
			model.addAttribute("listTeamXUsers", txService.listarUsuariosXgrupo(id));
			model.addAttribute("idTeam", id);
			model.addAttribute("agenda", new Agenda());
			model.addAttribute("listAgendas", aService.listarRecordatoriosXgrupo(id));
			return "grupos";
	        }
	        else {
	        	model.addAttribute("team", new Team());
	        	model.addAttribute("mensaje2", "No pertences al Grupo");
	        	model.addAttribute("listaGrupos", tService.listar());
	    		model.addAttribute("cuenta", cuenta2);
	            model.addAttribute("idCuenta", cuenta2.getIdUser());
	        	return "listTeam";
	        }
	        
		}
	
	
	


	@RequestMapping("/registrarRecor/{id}")
	public String registrar(@PathVariable int id,@ModelAttribute @Valid Agenda objAgenda, BindingResult binRes, Model model) 
			throws ParseException
	{
	if (binRes.hasErrors()) {
		model.addAttribute("listaUsuarios",aService.listar());
		return "message";
	}
	else {
		Optional<Team> team = tService.listarid(id);
		Date requestday=new Date();
		objAgenda.setDate(requestday);
		objAgenda.setTeam(team.get());
		boolean flag = aService.insertar(objAgenda);
		if (flag) {
			String cadena="redirect:/team/detailgrupo/"+ id;
			return cadena;
		}
		else {
			model.addAttribute("mensaje", "Sucedio un error");
			String cadena="redirect:/team/detailgrupo/"+ id;
			return cadena;
		}
	}
	}

	
	@RequestMapping("/busqueda")
	public String buscar(Map<String, Object> model, @ModelAttribute Team team ) throws ParseException {
		List<Team> listaTeams;
		team.setNameTeam(team.getNameTeam());
		listaTeams = tService.buscarNombre(team.getNameTeam());
		
		if (listaTeams.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaGrupos", listaTeams);	
		model.put("team", new Team());
		model.put("cuenta", cuenta2);
        model.put("idCuenta", cuenta2.getIdUser());
		return "listTeam";
	}
	

}
