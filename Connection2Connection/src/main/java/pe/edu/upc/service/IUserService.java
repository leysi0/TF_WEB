package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.model.Users;

public interface IUserService {
	public boolean insertar(Users user);
	public boolean modificar(Users user);
	public void eliminar(int idUser);
	public Optional<Users> listarId(int idUser);
	List<Users> listar();
	List<Users> buscarNombre(String nameUser);
	List<Users> buscarInstitucion(String institutionUser);
	List<Users> buscarOcupacion(String occupationUser);
}
