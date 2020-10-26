package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
