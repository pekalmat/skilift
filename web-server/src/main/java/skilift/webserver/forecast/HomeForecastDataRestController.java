package skilift.webserver.forecast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import skilift.webserver.cors.CorsHeaderData;
import skilift.webserver.entities.WeatherType;
import skilift.webserver.repositories.WeatherTypeRepository;

@RestController
public class HomeForecastDataRestController {

	@Autowired
	private WeatherTypeRepository weatherTypeRepository;
	
	/**
     * REST-Ressource für URL /skiapp/forecastChartData (GET)
     * @return HTTP-Response mit immer gleichen "dummy" forecast chart data (automatisch als JSON serialisiert)
     */
    @RequestMapping(value = "/skiapp/forecastChartData", method = RequestMethod.GET)
    public ResponseEntity<List<ForecastDay>> getForecastChartData(){
    	List<ForecastDay> forecastData = generateForecastData();
    	return new ResponseEntity<List<ForecastDay>>(forecastData, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
                
    }
	
    private List<ForecastDay> generateForecastData() {
    	List<WeatherType> allWeatherTypes = weatherTypeRepository.findAll();
    	List<ForecastDay> forecastData = new ArrayList<ForecastDay>();
    	forecastData.add(new ForecastDay(getDayLabel(0), 2478, 40,  allWeatherTypes.get(0)));
    	forecastData.add(new ForecastDay(getDayLabel(1), 5267, 80, allWeatherTypes.get(1)));
    	forecastData.add(new ForecastDay(getDayLabel(2), 734, 10, allWeatherTypes.get(2)));
    	forecastData.add(new ForecastDay(getDayLabel(3), 784, 10, allWeatherTypes.get(3)));
    	forecastData.add(new ForecastDay(getDayLabel(4), 433, 5, allWeatherTypes.get(4)));
    	return forecastData;
    }
    
    private String getDayLabel(Integer incrementDay) {
    	String dayLabel = null;
    	Calendar calendar = Calendar.getInstance();
    	int day = calendar.get(Calendar.DAY_OF_WEEK) + incrementDay;
    	if (day > 7) {
    		day = day - 7;
    	}
    	switch (day) {
    	    case Calendar.MONDAY:
    	        dayLabel = "MO";
    	        break;
    	    case Calendar.TUESDAY:
    	    	dayLabel = "DI";
    	        break;
    	    case Calendar.WEDNESDAY:
    	    	dayLabel = "MI";
    	        break;
    	    case Calendar.THURSDAY:
    	    	dayLabel = "DO";
    	        break;
    	    case Calendar.FRIDAY:
    	    	dayLabel = "FR";
    	        break;
    	    case Calendar.SATURDAY:
    	    	dayLabel = "SA";
    	        break;
    	    case Calendar.SUNDAY:
    	    	dayLabel = "SO";
    	    break;
    	}
    	return dayLabel;
    }
    
}
