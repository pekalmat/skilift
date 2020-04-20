package skilift.webserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"gastronomyType"})
	}
)
public class GastronomyType implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String gastronomyType;
	
	public GastronomyType() {
	}
	
	public GastronomyType(String gastronomyType) {
		this.gastronomyType = gastronomyType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGastronomyType() {
		return gastronomyType;
	}

	public void setGastronomyType(String gastronomyType) {
		this.gastronomyType = gastronomyType;
	}
	
}