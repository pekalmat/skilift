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
public class GastronomyUtilization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "gastronomy_id")
	private Gastronomy gastronomy;
	
	private Integer utilization;
	
	private Date timestamp;
	
	@ManyToOne
	@JoinColumn(name = "weather_id")
	private Weather weather;
	
	private Boolean isWorkday;
	
	public GastronomyUtilization() {
	}
	
	public GastronomyUtilization(Gastronomy gastronomy, Integer utilization, Date timestamp, Weather weather, Boolean isWorkday) {
		this.gastronomy = gastronomy;
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

	public Gastronomy getGastronomy() {
		return gastronomy;
	}

	public void setGastronomy(Gastronomy gastronomy) {
		this.gastronomy = gastronomy;
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

	public Integer getUtilization() {
		return utilization;
	}

	public void setUtilization(Integer utilization) {
		this.utilization = utilization;
	}
	
}