package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {

	@Query("from Users u where u.nameUser like %:nameUser%")
	List<Users> buscarNombre(@Param("nameUser") String nameUser);
	
	@Query("from Users u where u.nameUser like %:institutionUser%")
	List<Users> buscarInstitucion(@Param("institutionUser") String institutionUser);
	
	@Query("from Users u where u.nameUser like %:occupationUser%")
	List<Users> buscarOcupacion(@Param("occupationUser") String occupationUser);
	
	@Query("from Users u where upper(u.email)=upper(:parametro)")
	public Users findByCorreo(@Param("parametro") String correo);
	
	
}
