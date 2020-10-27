package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Role;
import pe.edu.upc.model.User;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IRoleService;

@Service
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
	
	@Override
	@Transactional
	public List<Role> listar() {
		return dRole.findAll();
	}

	@Override
	@Transactional
	public Role search(int rol) {
		
		return dRole.searchRole(rol);
	}

	
}
