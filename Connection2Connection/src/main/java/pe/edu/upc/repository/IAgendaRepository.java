package pe.edu.upc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Agenda;

@Repository
public interface IAgendaRepository extends JpaRepository<Agenda, Integer> {
	@Query("from Agenda u where u.team.idTeam =:parametro")
	List<Agenda> listaRecordatoriosXGrupo(@Param("parametro") int parametro);
	
}
