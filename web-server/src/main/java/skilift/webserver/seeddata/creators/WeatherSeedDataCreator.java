package skilift.webserver.seeddata.creators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Weather;
import skilift.webserver.entities.WeatherType;
import skilift.webserver.repositories.WeatherRepository;
import skilift.webserver.repositories.WeatherTypeRepository;

@Component
public class WeatherSeedDataCreator {

	private WeatherTypeRepository weatherTypeRepository;
	private WeatherRepository weatherRepository;
	
	@Autowired
	public WeatherSeedDataCreator(WeatherTypeRepository weatherTypeRepository, WeatherRepository weatherRepository) {
		this.weatherTypeRepository = weatherTypeRepository;
		this.weatherRepository = weatherRepository;
	}
	
	public void deleteAll() {
		weatherRepository.deleteAll();
		weatherTypeRepository.deleteAll();		
	}
	
	public Map<String, WeatherType>  createWeatherTypes() {
		WeatherType sunny = new WeatherType("Sunny");
		weatherTypeRepository.save(sunny);
		WeatherType partlyCloudy = new WeatherType("Partly Cloudy");
		weatherTypeRepository.save(partlyCloudy);
		WeatherType cloudy = new WeatherType("Cloudy");
		weatherTypeRepository.save(cloudy);
		WeatherType snowing = new WeatherType("Snowing");
		weatherTypeRepository.save(snowing);
		WeatherType rainy = new WeatherType("Rainy");
		weatherTypeRepository.save(rainy);
		Map<String, WeatherType> weatherTypes = new HashMap<>();
		weatherTypes.put("sunny", sunny);
		weatherTypes.put("partlyCloudy", partlyCloudy);
		weatherTypes.put("cloudy", cloudy);
		weatherTypes.put("snowing", snowing);
		weatherTypes.put("rainy", rainy);
		return weatherTypes;
	}
	
	public List<Weather> createWeathers(Map<String, WeatherType> weatherTypes) {
		List<Weather> weathers = new ArrayList<>();
		for(WeatherType weatherType : weatherTypes.values()) {
			// create weather for each weatherType for negative temperatures down to -20
			for(int i = 0; i >= -20; i--) {
				Weather weather = new Weather(weatherType, i);
				weatherRepository.save(weather);
				weathers.add(weather);
			}
			// create weather for each weatherType for positive temperatures up to 20
			for(int i = 1; i <= 20; i++) {
				Weather weather = new Weather(weatherType, i);
				weatherRepository.save(weather);
				weathers.add(weather);
			}
		}
		return weathers;
	}
}
