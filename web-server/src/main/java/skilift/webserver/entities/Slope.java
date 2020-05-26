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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"name"})
	}
)
public class Slope implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	private Double length;
	
	@JsonIgnore
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="mapping_lift_slope", joinColumns=@JoinColumn(name="slope_id"), inverseJoinColumns=@JoinColumn(name="lift_id"))
	private Set<Lift> lifts;
	
	public Slope() {
	}
	
	public Slope(String name, Color color, Double length) {
		this.name = name;
		this.color = color;
		this.length = length;
		this.lifts = new HashSet<>();
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
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}
	
	public Set<Lift> getLifts() {
		return lifts;
	}

	public void setLifts(Set<Lift> lifts) {
		this.lifts = lifts;
	}
	
}