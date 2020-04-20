package skilift.webserver.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class LiftUtilization implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "lift_id")
	private Lift lift;
	
	private Integer utilization;
	
	private Date timestamp;
	
	@ManyToOne
	@JoinColumn(name = "weather_id")
	private Weather weather;
	
	private Boolean isWorkday;
	
	public LiftUtilization() {
	}
	
	public LiftUtilization(Lift lift, Integer utilization, Date timestamp, Weather weather, Boolean isWorkday) {
		this.lift = lift;
		this.utilization = utilization;
		this.timestamp = timestamp;
		this.weather = weather;
		this.isWorkday = isWorkday;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public Boolean isWorkday() {
		return isWorkday;
	}
	
	public void setisWorkday(Boolean isWorkday) {
		this.isWorkday = isWorkday;
	}

	public Lift getLift() {
		return lift;
	}

	public void setLift(Lift lift) {
		this.lift = lift;
	}

	public Integer getUtilization() {
		return utilization;
	}

	public void setUtilization(Integer utilization) {
		this.utilization = utilization;
	}

	public Boolean getIsWorkday() {
		return isWorkday;
	}

	public void setIsWorkday(Boolean isWorkday) {
		this.isWorkday = isWorkday;
	}
}