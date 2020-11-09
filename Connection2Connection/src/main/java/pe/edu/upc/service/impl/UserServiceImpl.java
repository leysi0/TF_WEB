package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository dUser;

	@Override
	@Transactional
	public boolean insertar(Users user) {
		Users objUser = dUser.save(user);
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional	
	public boolean modificar(Users user) {
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
	public Optional<Users> listarId(int idUser) {
		return dUser.findById(idUser);		
	}

	@Override
	@Transactional
	public List<Users> listar() {
		return dUser.findAll();
	}

	@Override
	@Transactional
	public List<Users> buscarNombre(String nameUser) {
		return dUser.buscarNombre(nameUser);
	}
	
	@Override
	@Transactional
	public List<Users> buscarInstitucion(String institutionUser) {
		return dUser.buscarInstitucion(institutionUser);
	}
	
	@Override
	@Transactional
	public List<Users> buscarOcupacion(String occupationUser) {
		return dUser.buscarOcupacion(occupationUser);
	}
	
}
