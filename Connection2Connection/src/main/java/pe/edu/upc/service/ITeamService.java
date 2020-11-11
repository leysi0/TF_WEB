package pe.edu.upc.service;

import java.util.List;



import pe.edu.upc.model.Team;


public interface ITeamService {
	public boolean insertar(Team team);
	public void eliminar(int idteam);
	List<Team> listar();
}
