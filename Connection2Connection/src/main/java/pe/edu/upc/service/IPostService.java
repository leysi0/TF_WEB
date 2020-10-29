package pe.edu.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.upc.model.Post;
import pe.edu.upc.model.Role;


public interface IPostService {
	public boolean insertar(Post post);
	public void eliminar(int idpost);
	List<Post> listar();
}
