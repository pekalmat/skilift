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
@SequenceGenerator(initialValue = 1, name = "utilizationidgenerator", sequenceName = "lift_utilization_sequence")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LiftUtilization extends Utilization {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "lift_id")
	private Lift lift;
	
	public LiftUtilization() {
	}
	
	public LiftUtilization(Lift lift, Integer utilization, Date timestamp, Weather weather, Boolean isWorkday) {
		super(utilization, timestamp, weather, isWorkday);
		this.lift = lift;
	}

	public Lift getLift() {
		return lift;
	}

	public void setLift(Lift lift) {
		this.lift = lift;
	}
}