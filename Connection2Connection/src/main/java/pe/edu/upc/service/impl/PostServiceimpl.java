package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Post;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IPostRepository;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IPostService;
import pe.edu.upc.service.IRoleService;

@Service
public class PostServiceimpl implements IPostService {
	
	@Autowired
	private IPostRepository pPost;

	@Override
	@Transactional
	public boolean insertar(Post post) {
		Post objPost = pPost.save(post);
		if (objPost == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void eliminar(int idpost) {
		pPost.deleteById(idpost);
		
	}
	
	@Override
	@Transactional
	public List<Post> listar() {
		return pPost.findAll();
	}

	
}
