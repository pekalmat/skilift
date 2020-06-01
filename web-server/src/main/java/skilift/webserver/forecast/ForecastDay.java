package skilift.webserver.forecast;

import java.io.Serializable;

import skilift.webserver.entities.WeatherType;

public class ForecastDay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dayLabel;
	private Integer dayForecast;
	private Integer dayForecastPercentage;
	private WeatherType weatherType;
	
	public ForecastDay(
			String dayLabel,
			Integer dayForecast,
			Integer dayForecastPercentage,
			WeatherType weatherType) {
		this.dayLabel = dayLabel;
		this.dayForecast = dayForecast;
		this.dayForecastPercentage = dayForecastPercentage;
		this.weatherType = weatherType;
	}

	public String getDayLabel() {
		return dayLabel;
	}

	public void setDayLabel(String dayLabel) {
		this.dayLabel = dayLabel;
	}

	public Integer getDayForecast() {
		return dayForecast;
	}

	public void setDayForecast(Integer dayForecast) {
		this.dayForecast = dayForecast;
	}

	public Integer getDayForecastPercentage() {
		return dayForecastPercentage;
	}

	public void setDayForecastPercentage(Integer dayForecastPercentage) {
		this.dayForecastPercentage = dayForecastPercentage;
	}

	public WeatherType getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(WeatherType weatherType) {
		this.weatherType = weatherType;
	}

}