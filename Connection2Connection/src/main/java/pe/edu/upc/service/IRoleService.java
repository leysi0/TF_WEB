package pe.edu.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.upc.model.Role;


public interface IRoleService {
	public boolean insertar(Role role);
	public void eliminar(int idRol);
	List<Role> listar();
	public Role search(int rol);
}
