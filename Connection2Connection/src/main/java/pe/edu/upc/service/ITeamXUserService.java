package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Post;
import pe.edu.upc.model.Team;
import pe.edu.upc.model.TeamXUser;


public interface ITeamXUserService {
	List<TeamXUser> listarUsuariosXgrupo(int id);
}
