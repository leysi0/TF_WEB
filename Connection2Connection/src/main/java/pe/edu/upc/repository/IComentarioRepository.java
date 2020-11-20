package pe.edu.upc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Comentario;
import pe.edu.upc.model.Users;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query("from Comentario c where c.post.idPost = :Parameter")
	List<Comentario> buscarId(@Param("Parameter") int Parameter);
}
