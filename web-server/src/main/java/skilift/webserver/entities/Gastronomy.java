package skilift.webserver.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"name"})
	}
)
public class Gastronomy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String type;
	
	private Integer indoorSeats;
	
	private Integer outdoorSeats;
	
	private String speciality;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="mapping_lift_gastronomy", joinColumns=@JoinColumn(name="gastronomy_id"), inverseJoinColumns=@JoinColumn(name="lift_id"))
	private Set<Lift> lifts;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gastronomy")
	private Set<GastronomyUtilization> gastronomyutilization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIndoorSeats() {
		return indoorSeats;
	}

	public void setIndoorSeats(Integer indoorSeats) {
		this.indoorSeats = indoorSeats;
	}

	public Integer getOutdoorSeats() {
		return outdoorSeats;
	}

	public void setOutdoorSeats(Integer outdoorSeats) {
		this.outdoorSeats = outdoorSeats;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Set<Lift> getLifts() {
		return lifts;
	}

	public void setLifts(Set<Lift> lifts) {
		this.lifts = lifts;
	}

	public Set<GastronomyUtilization> getGastronomyutilization() {
		return gastronomyutilization;
	}

	public void setGastronomyutilization(Set<GastronomyUtilization> gastronomyutilization) {
		this.gastronomyutilization = gastronomyutilization;
	}
	
}