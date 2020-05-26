package skilift.webserver.seeddata.creators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.GastronomyUtilization;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftUtilization;
import skilift.webserver.entities.Weather;
import skilift.webserver.repositories.GastronomyUtilizationRepository;
import skilift.webserver.repositories.LiftUtilizationRepository;

@Component
public class UtilizationSeedDataCreator {
	
	private GastronomyUtilizationRepository gastronomyUtilizationRepository;
	private LiftUtilizationRepository liftUtilizationRepository;
	
	public UtilizationSeedDataCreator(GastronomyUtilizationRepository gastronomyUtilizationRepository, LiftUtilizationRepository liftUtilizationRepository) {
		this.gastronomyUtilizationRepository = gastronomyUtilizationRepository;
		this.liftUtilizationRepository = liftUtilizationRepository;
	}
	
	public void deleteAll() {
		gastronomyUtilizationRepository.deleteAll();
		liftUtilizationRepository.deleteAll();
	}

	@SuppressWarnings("deprecation")
	public Integer createLiftAndGastronomyUtilizations(List<Weather> weathers, Map<String, Lift> liftsMap, Map<String, Gastronomy> gastronomiesMap) {
		Collection<Gastronomy> gastronomies = gastronomiesMap.values();
		Collection<Lift> lifts = liftsMap.values();
		Collection<GastronomyUtilization> gastronomyUtilizations = new ArrayList<>();
		Collection<LiftUtilization> liftUtilizations = new ArrayList<>();
		for(Weather weather : weathers) {
			// generate random utilization between 10 and 100
			Random random = new Random();
			int low = 10;
			int high = 100;
			int randomUtilization;
			for(int day = 0; day <=7; day++) {
				for(int hour = 7; hour <= 18; hour++) {
					for(Gastronomy gastronomy : gastronomies) {
						//create gastro utilization 1 per hour between 7:00 and 18:00 for workday
						randomUtilization = random.nextInt(high-low) + low;
						GastronomyUtilization gastronomyUtilizationWorkday = new GastronomyUtilization(gastronomy, randomUtilization, new Date(2020, 1, day, hour, 0), weather, true);
						gastronomyUtilizations.add(gastronomyUtilizationWorkday);
						//create gastro utilization 1 per hour between 7:00 and 18:00 for workday
						randomUtilization = random.nextInt(high-low) + low;
						GastronomyUtilization gastronomyUtilizationNonWorkday = new GastronomyUtilization(gastronomy, randomUtilization, new Date(2020, 1, day, hour, 0), weather, true);
						gastronomyUtilizations.add(gastronomyUtilizationNonWorkday);
					}
					for(Lift lift : lifts) {
						//create gastro utilization 1 per hour between 7:00 and 18:00 for workday
						randomUtilization = random.nextInt(high-low) + low;
						LiftUtilization liftUtilizationWorkday = new LiftUtilization(lift, randomUtilization, new Date(2020, 1, day, hour, 0), weather, true);
						liftUtilizations.add(liftUtilizationWorkday);
						//create gastro utilization 1 per hour between 7:00 and 18:00 for workday
						randomUtilization = random.nextInt(high-low) + low;
						LiftUtilization liftUtilizationNonWorkday = new LiftUtilization(lift, randomUtilization, new Date(2020, 1, day, hour, 0), weather, true);
						liftUtilizations.add(liftUtilizationNonWorkday);
					}
				}
			}
		}
		gastronomyUtilizationRepository.saveAll(gastronomyUtilizations);
		liftUtilizationRepository.saveAll(liftUtilizations);
		return gastronomyUtilizations.size() + liftUtilizations.size();
	}
	
}
