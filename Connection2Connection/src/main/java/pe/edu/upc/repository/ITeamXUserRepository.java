package pe.edu.upc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.upc.model.TeamXUser;

@Repository
public interface ITeamXUserRepository extends JpaRepository<TeamXUser, Integer> {
	@Query("from TeamXUser u where u.team.idTeam =:parametro")
	List<TeamXUser> listaUsuariosXGrupo(@Param("parametro") int parametro);
	
	@Query("select count(u.users.idUser) from TeamXUser u where u.users.idUser =:parametro and u.team.idTeam =:parametro2")
	public int validarUsuariosXGrupo(@Param("parametro") int parametro,@Param("parametro2") int idTeam );

}

