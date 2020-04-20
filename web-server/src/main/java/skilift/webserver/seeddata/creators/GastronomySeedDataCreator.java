package skilift.webserver.seeddata.creators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.GastronomyType;
import skilift.webserver.repositories.GastronomyRepository;
import skilift.webserver.repositories.GastronomyTypeRepository;

@Component
public class GastronomySeedDataCreator {

	private GastronomyTypeRepository gastronomyTypeRepository;
	private GastronomyRepository gastronomyRepository;
	
	@Autowired
	public GastronomySeedDataCreator(GastronomyTypeRepository gastronomyTypeRepository, GastronomyRepository gastronomyRepository) {
		this.gastronomyTypeRepository = gastronomyTypeRepository;
		this.gastronomyRepository = gastronomyRepository;
	}
	
	public void deleteAll() {
		gastronomyTypeRepository.deleteAll();
		gastronomyRepository.deleteAll();
		gastronomyTypeRepository.deleteAll();
	}
	
	public Map<String, GastronomyType> createGastronomyTypes(){
		GastronomyType bar = new GastronomyType("Bar");
		gastronomyTypeRepository.save(bar);
		GastronomyType bergrestaurant = new GastronomyType("Bergrestaurant");
		gastronomyTypeRepository.save(bergrestaurant);
		GastronomyType restaurant = new GastronomyType("Restaurant");
		gastronomyTypeRepository.save(restaurant);
		Map<String, GastronomyType> gastronomyTypes = new HashMap<>();
		gastronomyTypes.put("bar", bar);
		gastronomyTypes.put("bergrestaurant", bergrestaurant);
		gastronomyTypes.put("restaurant", restaurant);
		return gastronomyTypes;
	}
	
	public Map<String, Gastronomy> createGastronomies(Map<String, GastronomyType> gastronomyTypes) {
		Gastronomy barLaCana = new Gastronomy("Bar la Cana", gastronomyTypes.get("bar"), 0, 0, "Spcialität?");
		gastronomyRepository.save(barLaCana);
		Gastronomy prumanPrui = new Gastronomy("Bergrestaurant Prüman Prui", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(prumanPrui);
		Gastronomy laPalmaBar = new Gastronomy("La Palma Bar", gastronomyTypes.get("bar"), 0, 0,"Spcialität?");
		gastronomyRepository.save(laPalmaBar);
		Gastronomy chammannaNalunsGastro = new Gastronomy("Bergrestaurant Chamanna Naluns", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(chammannaNalunsGastro);
		Gastronomy laMotta = new Gastronomy("Bergrestaurant la Motta", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(laMotta);
		Gastronomy laCharpenna = new Gastronomy("Bergrestaurant La Charpenna", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(laCharpenna);
		Gastronomy barMarMotta = new Gastronomy("Bar Mar-Motta", gastronomyTypes.get("bar"), 0, 0, "Spcialität?");
		gastronomyRepository.save(barMarMotta);
		Gastronomy vivaBar = new Gastronomy("Viva Bar", gastronomyTypes.get("bar"), 0, 0, "Spcialität?");
		gastronomyRepository.save(vivaBar);
		Gastronomy alpetta = new Gastronomy("Bergrestaurant Alpetta", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(alpetta);
		Gastronomy somiBar = new Gastronomy("Sömi Bar", gastronomyTypes.get("bergrestaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(somiBar);
		Gastronomy vastur = new Gastronomy("Restaurant Vastur", gastronomyTypes.get("restaurant"), 0, 0, "Spcialität?");
		gastronomyRepository.save(vastur);
		Map<String, Gastronomy> gastronomies = new HashMap<>();
		gastronomies.put("barLaCana", barLaCana);
		gastronomies.put("prumanPrui", prumanPrui);
		gastronomies.put("laPalmaBar", laPalmaBar);
		gastronomies.put("chammannaNalunsGastro", chammannaNalunsGastro);
		gastronomies.put("laMotta", laMotta);
		gastronomies.put("laCharpenna", laCharpenna);
		gastronomies.put("barMarMotta", barMarMotta);
		gastronomies.put("vivaBar", vivaBar);
		gastronomies.put("alpetta", alpetta);
		gastronomies.put("somiBar", somiBar);
		gastronomies.put("vastur", vastur);
		return gastronomies;
	}
}