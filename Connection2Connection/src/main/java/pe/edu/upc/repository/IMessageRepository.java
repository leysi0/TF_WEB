package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Users;


@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {

	@Query("from Message m where m.correoEmisor = :parametro")
	List<Message> listaEnviados(@Param("parametro") String correoEmisor); //lista de correos enviados
	
	@Query("from Message m where m.user.email = :parametro")
	List<Message> listaBandeja(@Param("parametro") String correoReceptor); //lista de correos de bandeja
	
	@Query("from Message m where m.idMessage=:parametro")
	public Message findByMessage(@Param("parametro") int id);
}
