package pe.edu.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.upc.model.Comentario;
import pe.edu.upc.model.Role;


public interface IComentarioService {
	public boolean insertar(Comentario comentario);
	public void eliminar(int idComentario);
	List<Comentario> listar();
	List<Comentario> listarComentariosxPublicacion(int id);
}
