package pe.edu.upc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.model.TeamXUser;
import pe.edu.upc.repository.ITeamXUserRepository;
import pe.edu.upc.service.ITeamXUserService;

@Service
public class TeamXUserServiceimpl implements ITeamXUserService {
	
	@Autowired
	private ITeamXUserRepository dTeamX;

	@Override
	public List<TeamXUser> listarUsuariosXgrupo(int id) {
		return dTeamX.listaUsuariosXGrupo(id);
	}
}
