package pe.edu.upc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import pe.edu.upc.model.Team;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Integer> {

}
