package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.User;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository dUser;

	@Override
	@Transactional
	public boolean insertar(User user) {
		User objUser = dUser.save(user);
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional	
	public boolean modificar(User user) {
		boolean flag = false;
		try {
			dUser.save(user);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche...");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idUser) {
		dUser.deleteById(idUser);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> listarId(int idUser) {
		return dUser.findById(idUser);		
	}

	@Override
	@Transactional
	public List<User> listar() {
		return dUser.findAll();
	}

	@Override
	@Transactional
	public List<User> buscarNombre(String nameUser) {
		return dUser.buscarNombre(nameUser);
	}
	
	@Override
	@Transactional
	public List<User> buscarInstitución(String institutionUser) {
		return dUser.buscarInstitución(institutionUser);
	}
	
	@Override
	@Transactional
	public List<User> buscarOcupación(String occupationUser) {
		return dUser.buscarOcupación(occupationUser);
	}
	
}
