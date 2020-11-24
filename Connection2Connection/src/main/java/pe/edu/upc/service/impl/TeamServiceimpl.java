package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Team;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.ITeamRepository;
import pe.edu.upc.repository.ITeamXUserRepository;
import pe.edu.upc.service.ITeamService;

@Service
public class TeamServiceimpl implements ITeamService {
	
	@Autowired
	private ITeamRepository dTeam;
	
	@Autowired
	private ITeamXUserRepository dTeamx;

	@Override
	@Transactional
	public boolean insertar(Team team) {
		Team objRole = dTeam.save(team);
		if (objRole == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional	
	public boolean modificar(Team team) {
		boolean flag = false;
		
		try {
		
			dTeam.save(team);
			flag = true;

		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idmessage) {
		dTeam.deleteById(idmessage);
		
	}
	
	@Override
	@Transactional
	public List<Team> listar() {
		return dTeam.findAll();
	}

	@Override
	public Optional<Team> listarid(int id) {
		
		return dTeam.findById(id);
	}

	@Override
	public int validar(int idUser, int idTeam) {
		int rpta = 0;
		rpta = dTeamx.validarUsuariosXGrupo(idUser, idTeam);
		return rpta;
	}

	@Override
	public List<Team> buscarNombre(String nameTeam) {
		// TODO Auto-generated method stub
		return dTeam.buscarNombre(nameTeam);
	}

	
}
