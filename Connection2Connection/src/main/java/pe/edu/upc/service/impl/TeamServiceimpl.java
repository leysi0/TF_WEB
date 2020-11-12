package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.Team;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IMessageRepository;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.repository.ITeamRepository;
import pe.edu.upc.service.IMessageService;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.ITeamService;

@Service
public class TeamServiceimpl implements ITeamService {
	
	@Autowired
	private ITeamRepository dTeam;

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
	public void eliminar(int idmessage) {
		dTeam.deleteById(idmessage);
		
	}
	
	@Override
	@Transactional
	public List<Team> listar() {
		return dTeam.findAll();
	}

	
}
