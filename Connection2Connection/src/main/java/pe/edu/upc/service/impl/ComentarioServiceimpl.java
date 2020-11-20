package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Comentario;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IComentarioRepository;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IComentarioService;
import pe.edu.upc.service.IRoleService;

@Service
public class ComentarioServiceimpl implements IComentarioService {
	
	@Autowired
	private IComentarioRepository dComentario;

	@Override
	@Transactional
	public boolean insertar(Comentario comentario) {
		Comentario objComentario = dComentario.save(comentario);
		if (objComentario == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void eliminar(int idComentario) {
		dComentario.deleteById(idComentario);
		
	}
	
	@Override
	@Transactional
	public List<Comentario> listar() {
		return dComentario.findAll();
	}

	@Override
	public List<Comentario> listarComentariosxPublicacion(int id) {
		// TODO Auto-generated method stub
		return dComentario.buscarId(id);
	}


	
}
