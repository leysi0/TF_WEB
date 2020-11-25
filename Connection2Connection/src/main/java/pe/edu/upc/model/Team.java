package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="team")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeam;
	
	@Column(name = "nameTeam", length=50, nullable =false)
	private String nameTeam;
	
	
	@Column(name="amountTeam", nullable=false)
	private int amountTeam;
	
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="description", length=200, nullable=false)
	private String description;
	
	
	@Column(name="fecha", length=60, nullable=false)
	private Date date;
	
	@OneToMany(mappedBy="team", cascade= CascadeType.ALL)
	private Set<TeamXUser> teamXUser= new HashSet<>();
	

	public int getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}


	public String getNameTeam() {
		return nameTeam;
	}


	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}


	public int getAmountTeam() {
		return amountTeam;
	}


	public void setAmountTeam(int amountTeam) {
		this.amountTeam = amountTeam;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Set<TeamXUser> getTeamXUser() {
		return teamXUser;
	}


	public void setTeamXUser(Set<TeamXUser> teamXUser) {
		this.teamXUser = teamXUser;
	}






}
