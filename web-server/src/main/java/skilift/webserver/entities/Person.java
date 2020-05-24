package skilift.webserver.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"email"})
	}
)
public class Person implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotNull
    private String firstName;
    
	@NotNull
    private String surname;
    
	@NotNull
	private String email;
    
	@NotNull
    private String password;
    
    private Integer ticketNr;
    
    @JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private Set<Reservation> reservations;
	
	public Person() {
	}
	
	public Person(String firstName, String surname, String email, String password, Integer ticketNr) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.ticketNr = ticketNr;
		this.reservations = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTicketNr() {
		return ticketNr;
	}

	public void setTicketNr(Integer ticketNr) {
		this.ticketNr = ticketNr;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
   
}