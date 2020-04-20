package skilift.webserver.seeddata.creators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Color;
import skilift.webserver.entities.Slope;
import skilift.webserver.repositories.ColorRepository;
import skilift.webserver.repositories.SlopeRepository;

@Component
public class SlopeSeedDataCreator {

	private ColorRepository colorRepository;
	private SlopeRepository slopeRepository;
	
	@Autowired
	public SlopeSeedDataCreator(ColorRepository colorRepository, SlopeRepository slopeRepository) {
		this.colorRepository = colorRepository;
		this.slopeRepository = slopeRepository;
	}
	
	public void deleteAll() {
		colorRepository.deleteAll();
		slopeRepository.deleteAll();
	}
	
	public Map<String, Color> createColors() {
		Color blue = new Color("blue", "#0000FF", "easy");
		colorRepository.save(blue);
		Color red = new Color("red", "#FF0000", "intermediate");
		colorRepository.save(red);
		Color black = new Color("black", "	#000000", "expert");
		colorRepository.save(black);
		Map<String, Color> colors = new HashMap<>();
		colors.put("blue", blue);
		colors.put("red", red);
		colors.put("black", black);
		return colors;
	}
	
	public Map<String, Slope> createSlopes(Map<String, Color> colors) {
		Slope prasurasSlope = new Slope("Prasüras", colors.get("blue"), 1000.0);
		slopeRepository.save(prasurasSlope);
		Slope pruiSlope = new Slope("Prui", colors.get("red"), 1000.0);
		slopeRepository.save(pruiSlope);
		Slope nateasSlope = new Slope("Nateas", colors.get("blue"), 1000.0);
		slopeRepository.save(nateasSlope);
		Slope derbySlope = new Slope("Derby (Scuol)", colors.get("red"), 1000.0);
		slopeRepository.save(derbySlope);
		Slope rachognaSlope = new Slope("Rachögna", colors.get("blue"), 1000.0);
		slopeRepository.save(rachognaSlope);
		Slope nalunsSlope = new Slope("Naluns", colors.get("red"), 1000.0);
		slopeRepository.save(nalunsSlope);
		Slope snowParkMottaNalunsSlope = new Slope("Snowpark Motta Naluns", colors.get("red"), 1000.0);
		slopeRepository.save(snowParkMottaNalunsSlope);
		Slope schliveraSlope = new Slope("Schlivera", colors.get("blue"), 1000.0);
		slopeRepository.save(schliveraSlope);
		Slope plantaSlope = new Slope("Planta", colors.get("blue"), 1000.0);
		slopeRepository.save(plantaSlope);
		Slope jonvraiSlope = new Slope("Jonvrai", colors.get("blue"), 1000.0);
		slopeRepository.save(jonvraiSlope);
		Slope rennpiste = new Slope("Rennpiste", colors.get("red"), 1000.0);
		slopeRepository.save(rennpiste);
		Slope spiFisSlope = new Slope("Spi FIS", colors.get("red"), 1000.0);
		slopeRepository.save(spiFisSlope);
		Slope nevinGalmariniSlope = new Slope("Nevin Galmarini", colors.get("black"), 1000.0);
		slopeRepository.save(nevinGalmariniSlope);
		Slope clunasSlope = new Slope("Clünas", colors.get("red"), 1000.0);
		slopeRepository.save(clunasSlope);
		Slope valRuchnaSlope = new Slope("Val Ruschna", colors.get("red"), 1000.0);
		slopeRepository.save(valRuchnaSlope);
		Slope crapAlbFisSlope = new Slope("Crap alb FIS", colors.get("black"), 1000.0);
		slopeRepository.save(crapAlbFisSlope);
		Slope costeraFisSlope = new Slope("Costera FIS", colors.get("black"), 1000.0);
		slopeRepository.save(costeraFisSlope);
		Slope motDaRiSlope = new Slope("Mot da Ri", colors.get("blue"), 1000.0);
		slopeRepository.save(motDaRiSlope);
		Slope eraSlope = new Slope("Era", colors.get("blue"), 1000.0);
		slopeRepository.save(eraSlope);
		Slope eraChampatschSlope = new Slope("Era Champatsch", colors.get("blue"), 1000.0);
		slopeRepository.save(eraChampatschSlope);
		Slope pizNairSlope = new Slope("Piz Nair", colors.get("blue"), 1000.0);
		slopeRepository.save(pizNairSlope);
		Slope isolaPersaSlope = new Slope("Isola Persa", colors.get("red"), 1000.0);
		slopeRepository.save(isolaPersaSlope);
		Slope champatschSlope = new Slope("Champatsch", colors.get("red"), 1000.0);
		slopeRepository.save(champatschSlope);
		Slope viaChampatschSlope = new Slope("Via Champatsch", colors.get("blue"), 1000.0);
		slopeRepository.save(viaChampatschSlope);
		Slope muntanellaSlope = new Slope("Muntanella", colors.get("blue"), 1000.0);
		slopeRepository.save(muntanellaSlope);
		Slope motSlope = new Slope("Mot", colors.get("black"), 1000.0);
		slopeRepository.save(motSlope);
		Slope siertSlope = new Slope("Siert", colors.get("red"), 1000.0);
		slopeRepository.save(siertSlope);
		Slope traumpisteSlope = new Slope("Traumpiste", colors.get("red"), 1000.0);
		slopeRepository.save(traumpisteSlope);
		Map<String, Slope> slopes = new HashMap<>();
		slopes.put("prasurasSlope", prasurasSlope);
		slopes.put("pruiSlope", pruiSlope);
		slopes.put("nateasSlope", nateasSlope);
		slopes.put("prasurasSlope", prasurasSlope);
		slopes.put("derbySlope", derbySlope);
		slopes.put("rachognaSlope", rachognaSlope);
		slopes.put("nalunsSlope", nalunsSlope);
		slopes.put("snowParkMottaNalunsSlope", snowParkMottaNalunsSlope);
		slopes.put("schliveraSlope", schliveraSlope);
		slopes.put("plantaSlope", plantaSlope);
		slopes.put("jonvraiSlope", jonvraiSlope);
		slopes.put("rennpiste", rennpiste);
		slopes.put("spiFisSlope", spiFisSlope);
		slopes.put("nevinGalmariniSlope", nevinGalmariniSlope);
		slopes.put("clunasSlope", clunasSlope);
		slopes.put("valRuchnaSlope", valRuchnaSlope);
		slopes.put("crapAlbFisSlope", crapAlbFisSlope);
		slopes.put("costeraFisSlope", costeraFisSlope);
		slopes.put("motDaRiSlope", motDaRiSlope);
		slopes.put("eraSlope", eraSlope);
		slopes.put("eraChampatschSlope", eraChampatschSlope);
		slopes.put("pizNairSlope", pizNairSlope);
		slopes.put("isolaPersaSlope", isolaPersaSlope);
		slopes.put("champatschSlope", champatschSlope);
		slopes.put("viaChampatschSlope", viaChampatschSlope);
		slopes.put("muntanellaSlope", muntanellaSlope);
		slopes.put("motSlope", motSlope);
		slopes.put("siertSlope", siertSlope);
		slopes.put("traumpisteSlope", traumpisteSlope);
		return slopes;
	}	
}