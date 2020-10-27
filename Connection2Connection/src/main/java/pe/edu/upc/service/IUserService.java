package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.model.User;

public interface IUserService {
	public boolean insertar(User user);
	public boolean modificar(User user);
	public void eliminar(int idUser);
	public Optional<User> listarId(int idUser);
	List<User> listar();
	List<User> buscarNombre(String nameUser);
	List<User> buscarInstitucion(String institutionUser);
	List<User> buscarOcupacion(String occupationUser);
}
