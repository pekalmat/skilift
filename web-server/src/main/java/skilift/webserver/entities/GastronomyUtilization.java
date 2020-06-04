package skilift.webserver.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@SequenceGenerator(initialValue = 1, name = "utilizationidgenerator", sequenceName = "gastronomy_utilization_sequence")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GastronomyUtilization extends Utilization {	

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "gastronomy_id")
	private Gastronomy gastronomy;
	
	public GastronomyUtilization() {
	}
	
	public GastronomyUtilization(Gastronomy gastronomy, Integer utilization, Date timestamp, Weather weather, Boolean isWorkday) {
		super(utilization, timestamp, weather, isWorkday);
		this.gastronomy = gastronomy;
	}

	public Gastronomy getGastronomy() {
		return gastronomy;
	}

	public void setGastronomy(Gastronomy gastronomy) {
		this.gastronomy = gastronomy;
	}

}