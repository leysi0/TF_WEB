package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Post;
import pe.edu.upc.model.Team;


public interface ITeamService {
	public boolean insertar(Team team);
	public void eliminar(int idteam);
	List<Team> listar();
	Optional<Team> listarid(int id);
	public boolean modificar(Team team);
	public int  validar (int idUser,int idTeam);
}
