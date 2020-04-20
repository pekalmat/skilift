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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Lift implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "lift_type_id")
	private LiftType liftType;
	
	@ManyToOne
	@JoinColumn(name = "lift_status_id")
	private LiftStatus status;
	
	private Integer wait;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="mapping_lift_gastronomy", joinColumns=@JoinColumn(name="lift_id"), inverseJoinColumns=@JoinColumn(name="gastronomy_id"))
	private Set<Gastronomy> gastronomies;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="mapping_lift_slope", joinColumns=@JoinColumn(name="lift_id"), inverseJoinColumns=@JoinColumn(name="slope_id"))
	private Set<Slope> slopes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lift")
	private Set<LiftUtilization> liftUtilization;
	
	public Lift() {
	}
	
	public Lift(String name, LiftType liftType, LiftStatus status, Integer wait) {
		this.name = name;
		this.liftType = liftType;
		this.status = status;
		this.wait = wait;
		this.gastronomies = new HashSet<>();
		this.slopes = new HashSet<>();
		this.liftUtilization = new HashSet<>();
	}

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
	
	public LiftType getLiftType() {
		return liftType;
	}

	public void setLiftType(LiftType liftType) {
		this.liftType = liftType;
	}

	public LiftStatus getStatus() {
		return status;
	}

	public void setStatus(LiftStatus status) {
		this.status = status;
	}

	public Integer getWait() {
		return wait;
	}

	public void setWait(Integer wait) {
		this.wait = wait;
	}

	public Set<Gastronomy> getGastronomies() {
		return gastronomies;
	}

	public void setGastronomies(Set<Gastronomy> gastronomies) {
		this.gastronomies = gastronomies;
	}

	public Set<Slope> getSlopes() {
		return slopes;
	}

	public void setSlopes(Set<Slope> slopes) {
		this.slopes = slopes;
	}

	public Set<LiftUtilization> getLiftUtilization() {
		return liftUtilization;
	}

	public void setLiftUtilization(Set<LiftUtilization> liftUtilization) {
		this.liftUtilization = liftUtilization;
	}
	
}