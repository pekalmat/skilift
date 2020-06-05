package skilift.webserver.seeddata;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Color;
import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.GastronomyType;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftStatus;
import skilift.webserver.entities.LiftType;
import skilift.webserver.entities.Person;
import skilift.webserver.entities.Reservation;
import skilift.webserver.entities.SeatType;
import skilift.webserver.entities.Slope;
import skilift.webserver.entities.Weather;
import skilift.webserver.entities.WeatherType;
import skilift.webserver.seeddata.creators.GastronomySeedDataCreator;
import skilift.webserver.seeddata.creators.LiftSeedDataCreator;
import skilift.webserver.seeddata.creators.PersonReservationSeedDataCreator;
import skilift.webserver.seeddata.creators.SlopeSeedDataCreator;
import skilift.webserver.seeddata.creators.UtilizationSeedDataCreator;
import skilift.webserver.seeddata.creators.WeatherSeedDataCreator;

@Component
public class SeedDataLoader implements ApplicationRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeedDataLoader.class);

	private GastronomySeedDataCreator gastronomySeedDataCraetor;
	private LiftSeedDataCreator liftSeedDataCreator;
	private PersonReservationSeedDataCreator personReservationSeedDataCreator;
	private SlopeSeedDataCreator slopeSeedDataCreator;
	private UtilizationSeedDataCreator utilizationSeedDataCreator;
	private WeatherSeedDataCreator weatherSeedDataCreator;
	
	@Autowired
	public SeedDataLoader(
			GastronomySeedDataCreator gastronomySeedDataCraetor,
			LiftSeedDataCreator liftSeedDataCreator,
			PersonReservationSeedDataCreator personReservationSeedDataCreator,
			SlopeSeedDataCreator slopeSeedDataCreator,
			UtilizationSeedDataCreator utilizationSeedDataCreator,
			WeatherSeedDataCreator weatherSeedDataCreator) {
		this.gastronomySeedDataCraetor = gastronomySeedDataCraetor;
		this.liftSeedDataCreator = liftSeedDataCreator;
		this.personReservationSeedDataCreator = personReservationSeedDataCreator;
		this.slopeSeedDataCreator = slopeSeedDataCreator;
		this.utilizationSeedDataCreator = utilizationSeedDataCreator;
		this.weatherSeedDataCreator = weatherSeedDataCreator;
	}
		
	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("Start Loading Test Data...");
		//insertTestData();
		LOGGER.info("Test Data Successfully Loaded");
	}  
	
	private void insertTestData() {
		Map<String, LiftStatus> liftStatus = liftSeedDataCreator.createLiftStatus();
		LOGGER.info("... " + liftStatus.size() + " Lift Statuses loaded");
		Map<String, LiftType> liftTypes = liftSeedDataCreator.createLiftTypes();
		LOGGER.info("... " + liftTypes.size() + " Lift Statuses created");
		Map<String, Lift> lifts = liftSeedDataCreator.createLifts(liftTypes, liftStatus);
		LOGGER.info("... " + lifts.size() + " Lifts created");
		
		Map<String, Color> colors = slopeSeedDataCreator.createColors();
		LOGGER.info("... " + colors.size() + " Colors created");
		Map<String, Slope> slopes = slopeSeedDataCreator.createSlopes(colors);
		LOGGER.info("... " + slopes.size() + " Slopes created");
		
		liftSeedDataCreator.linkLiftsAndTargetSlopes(lifts, slopes);
		
		Map<String, GastronomyType> gastronomyTypes = gastronomySeedDataCraetor.createGastronomyTypes();
		LOGGER.info("... " + gastronomyTypes.size() + "Gastronomy Types created");
		Map<String, Gastronomy> gastronomies = gastronomySeedDataCraetor.createGastronomies(gastronomyTypes);
		LOGGER.info("... " + gastronomies.size() + " Gastronomies created");

		liftSeedDataCreator.linkLiftsAndGastronomies(lifts, gastronomies);
		LOGGER.info("... Lifts linked with Gastronomies and Slopes");
		
		Map<String, Person> persons = personReservationSeedDataCreator.createPersons();
		LOGGER.info("... " + persons.size() + " test Persons created");

		Map<String, SeatType> seatTypes = personReservationSeedDataCreator.createSeatTypes();
		LOGGER.info("... " + seatTypes.size() + " Seat Types created");
		Map<String, Reservation> reservations = personReservationSeedDataCreator.createReservations(persons, gastronomies, seatTypes);		
		LOGGER.info("... " + reservations.size() + " test Reservations created");
		
		Map<String, WeatherType> weatherTypes = weatherSeedDataCreator.createWeatherTypes();
		LOGGER.info("... " + weatherTypes.size() + " Weather Types created");
		List<Weather> weathers = weatherSeedDataCreator.createWeathers(weatherTypes);
		LOGGER.info("... " + weathers.size() + " Weather Data records created");
		
		Integer utilizationDataCount = utilizationSeedDataCreator.createLiftAndGastronomyUtilizations(weathers, lifts, gastronomies);
		LOGGER.info("... " + utilizationDataCount + " Lift and Gastronomy Utilization Data Records created");

	}
	
}