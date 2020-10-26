package pe.edu.upc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Role;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IRoleService;

public class RoleServiceimpl implements IRoleService {
	
	@Autowired
	private IRoleRepository dRole;

	@Override
	@Transactional
	public boolean insertar(Role role) {
		Role objRole = dRole.save(role);
		if (objRole == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void eliminar(int idRole) {
		dRole.deleteById(idRole);
		
	}
}
