/*package pe.edu.upc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="teamXUser")
public class TeamXUser implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name = "idTeam", nullable =false)
	private Team team;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idUser", nullable =false)
	private Users user;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
*/
