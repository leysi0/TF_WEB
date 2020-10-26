package pe.edu.upc.service;

import pe.edu.upc.model.Role;

public interface IRoleService {
	public boolean insertar(Role role);
	public void eliminar(int idRol);
}
