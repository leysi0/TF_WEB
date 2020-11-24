package pe.edu.upc.model;

import java.io.Serializable;

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
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeamXUser;
	
	
	@ManyToOne
	@JoinColumn(name = "idTeam", nullable =false)
	private Team team;
	
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable =false)
	private Users users;


	public int getIdTeamXUser() {
		return idTeamXUser;
	}


	public void setIdTeamXUser(int idTeamXUser) {
		this.idTeamXUser = idTeamXUser;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


}
