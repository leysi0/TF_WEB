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
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class Users implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	

	@Column(name="nameUser", length=60, nullable=false)
	private String nameUser;
	

	@Column(name="emailUser", length=60, nullable=false)
	private String email;
	

	@Column(name="adressUser", length=60, nullable=false)
	private String adress;

	@Column(name="phoneUser", length=9, nullable=false)
	private String phone;

	@Column(name="institutionUser", length=60, nullable=false)
	private String institution;
	

	@Column(name="genderUser", length=10, nullable=false)
	private String gender;
	

	@Column(name="occupationUser", length=60, nullable=false)
	private String occupation;
	
	@Column(name="dateUser", length=60, nullable=false)
	private Date date;
	
	@Column(name="contra", length=60, nullable=false)
	private String contra;
	
	@OneToOne
	@JoinColumn(name = "idRole", nullable =false)
	private Role role;
	
	@OneToMany(mappedBy="users", cascade= CascadeType.ALL)
	private Set<TeamXUser> teamXUser= new HashSet<>();
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public Set<TeamXUser> getTeamXUser() {
		return teamXUser;
	}

	public void setTeamXUser(Set<TeamXUser> teamXUser) {
		this.teamXUser = teamXUser;
	}



	
	
}
