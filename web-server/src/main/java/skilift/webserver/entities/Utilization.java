package skilift.webserver.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Utilization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilizationidgenerator")
    private Long id;
	
	private Integer utilization;
	
	private Date timestamp;
	
	@ManyToOne
	@JoinColumn(name = "weather_id")
	private Weather weather;
	
	private Boolean isWorkday;
	
	public Utilization() {
	}
	
	public Utilization(Integer utilization, Date timestamp, Weather weather, Boolean isWorkday) {
		this.utilization = utilization;
		this.timestamp = timestamp;
		this.weather = weather;
		this.isWorkday = isWorkday;
	}

	public Integer getUtilization() {
		return utilization;
	}

	public void setUtilization(Integer utilization) {
		this.utilization = utilization;
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

	public Boolean getIsWorkday() {
		return isWorkday;
	}

	public void setIsWorkday(Boolean isWorkday) {
		this.isWorkday = isWorkday;
	}

	public Long getId() {
		return id;
	}
	
}