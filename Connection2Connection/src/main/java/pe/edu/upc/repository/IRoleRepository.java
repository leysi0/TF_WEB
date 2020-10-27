package pe.edu.upc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

	@Query("from Role where idRole=:para")
	public Role searchRole(@Param("para") int idrole);
	
}
