package skilift.webserver.seeddata.creators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftStatus;
import skilift.webserver.entities.LiftType;
import skilift.webserver.entities.Slope;
import skilift.webserver.repositories.LiftRepository;
import skilift.webserver.repositories.LiftStatusRepository;
import skilift.webserver.repositories.LiftTypeRepository;

@Component
public class LiftSeedDataCreator {

	private LiftTypeRepository liftTypeRepository;
	private LiftStatusRepository liftStatusRepository;
	private LiftRepository liftRepository;
	
	@Autowired
	public LiftSeedDataCreator(LiftTypeRepository liftTypeRepository, LiftStatusRepository liftStatusRepository, LiftRepository liftRepository) {
		this.liftTypeRepository = liftTypeRepository;
		this.liftStatusRepository = liftStatusRepository;
		this.liftRepository = liftRepository;
	}
	
	public void deleteAll() {
		liftTypeRepository.deleteAll();
		liftStatusRepository.deleteAll();
		liftRepository.deleteAll();
	}
	
	public Map<String, LiftType> createLiftTypes() {
		LiftType chairLift4 = new LiftType(4, "Chair Lift");
		liftTypeRepository.save(chairLift4);
		LiftType chairLift6 = new LiftType(6, "Chair Lift");
		liftTypeRepository.save(chairLift6);
		LiftType gondolaLift = new LiftType(20, "Gondola Lift");
		liftTypeRepository.save(gondolaLift);
		LiftType dragLift = new LiftType(2, "Drag Lift");
		liftTypeRepository.save(dragLift);
		LiftType babyLift = new LiftType(1, "Baby Lift");
		liftTypeRepository.save(babyLift);
		Map<String, LiftType> liftTypes = new HashMap<>();
		liftTypes.put("chairLift4", chairLift4);
		liftTypes.put("chairLift6", chairLift6);
		liftTypes.put("gondolaLift", gondolaLift);
		liftTypes.put("dragLift", dragLift);
		liftTypes.put("babyLift", babyLift);
		return liftTypes;
	}
	
	public Map<String, LiftStatus> createLiftStatus() {
		LiftStatus open = new LiftStatus("Open");
		liftStatusRepository.save(open);
		LiftStatus closed = new LiftStatus("Closed");
		liftStatusRepository.save(closed);
		LiftStatus preparation = new LiftStatus("Preparation");
		liftStatusRepository.save(preparation);
		Map<String, LiftStatus> liftStatus = new HashMap<>();
		liftStatus.put("open", open);
		liftStatus.put("closed", open);
		liftStatus.put("preparation", open);
		return liftStatus;
	}
	
	public Map<String, Lift> createLifts(Map<String, LiftType> liftTypes, Map<String, LiftStatus> liftStatus) {
		Lift champatch1 = new Lift("Champatch I", liftTypes.get("dragLift"), liftStatus.get("preparation"), 5);
		liftRepository.save(champatch1);
		Lift champatch2 = new Lift("Champatch II", liftTypes.get("dragLift"), liftStatus.get("preparation"), 5);
		liftRepository.save(champatch2);
		Lift salaniva = new Lift("Salaniva", liftTypes.get("chairLift4"), liftStatus.get("closed"), 10);
		liftRepository.save(salaniva);
		Lift motDaRi = new Lift("Mot da Ri", liftTypes.get("chairLift4"), liftStatus.get("open"), 10);
		liftRepository.save(motDaRi);
		Lift clunas = new Lift("Clunas", liftTypes.get("chairLift4"), liftStatus.get("open"), 15);
		liftRepository.save(clunas);
		Lift naluns = new Lift("Naluns", liftTypes.get("chairLift6"), liftStatus.get("open"), 20);
		liftRepository.save(naluns);
		Lift prui = new Lift("Prui", liftTypes.get("chairLift6"), liftStatus.get("open"), 20);
		liftRepository.save(prui);
		Lift ftan = new Lift("Ftan", liftTypes.get("chairLift4"), liftStatus.get("open"), 20);
		liftRepository.save(ftan);
		Lift mottaNaluns = new Lift("Motta Naluns", liftTypes.get("gondolaLift"), liftStatus.get("open"), 10);
		liftRepository.save(mottaNaluns);
		Lift rachogna = new Lift("Rachögna", liftTypes.get("dragLift"), liftStatus.get("open"), 2);
		liftRepository.save(rachogna);
		Lift zauberTeppich1 = new Lift("Zauberteppich I", liftTypes.get("babyLift"), liftStatus.get("open"), 30);
		liftRepository.save(zauberTeppich1);
		Lift zauberTeppich2 = new Lift("Zauberteppich II", liftTypes.get("babyLift"), liftStatus.get("open"), 30);
		liftRepository.save(zauberTeppich2);
		Lift zauberTeppich3 = new Lift("Zauberteppich III", liftTypes.get("babyLift"), liftStatus.get("open"), 0);
		liftRepository.save(zauberTeppich3);
		Lift zauberTeppichFtan = new Lift("Zauberteppich Ftan", liftTypes.get("babyLift"), liftStatus.get("open"), 0);
		liftRepository.save(zauberTeppichFtan);
		Map<String, Lift> lifts = new HashMap<>();
		lifts.put("champatch1", champatch1);
		lifts.put("champatch2", champatch2);
		lifts.put("salaniva", salaniva);
		lifts.put("motDaRi", motDaRi);
		lifts.put("clunas", clunas);
		lifts.put("naluns", naluns);
		lifts.put("prui", prui);
		lifts.put("ftan", ftan);
		lifts.put("mottaNaluns", mottaNaluns);
		lifts.put("rachogna", rachogna);
		lifts.put("zauberTeppich1", zauberTeppich1);
		lifts.put("zauberTeppich2", zauberTeppich2);
		lifts.put("zauberTeppich3", zauberTeppich3);
		lifts.put("zauberTeppichFtan", zauberTeppichFtan);
		return lifts;
	}
	
	public void linkLiftsAndTargetSlopes(Map<String, Lift> lifts, Map<String, Slope> slopes) {
		// TODO review
		lifts.get("ftan").getSlopes().add(slopes.get("prasurasSlope"));
		lifts.get("ftan").getSlopes().add(slopes.get("nateasSlope"));
		lifts.get("prui").getSlopes().add(slopes.get("plantaSlope"));
		lifts.get("prui").getSlopes().add(slopes.get("clunasSlope"));
		lifts.get("prui").getSlopes().add(slopes.get("nevinGalmariniSlope"));
		lifts.get("mottaNaluns").getSlopes().add(slopes.get("schliveraSlope"));
		lifts.get("mottaNaluns").getSlopes().add(slopes.get("derbySlope"));
		lifts.get("rachogna").getSlopes().add(slopes.get("rachognaSlope"));
		lifts.get("naluns").getSlopes().add(slopes.get("nateasSlope"));
		lifts.get("naluns").getSlopes().add(slopes.get("schliveraSlope"));
		lifts.get("naluns").getSlopes().add(slopes.get("snowParkMottaNalunsSlope"));
		lifts.get("naluns").getSlopes().add(slopes.get("nalunsSlope"));
		lifts.get("naluns").getSlopes().add(slopes.get("jonvraiSlope"));
		lifts.get("clunas").getSlopes().add(slopes.get("plantaSlope"));
		lifts.get("clunas").getSlopes().add(slopes.get("nevinGalmariniSlope"));
		lifts.get("clunas").getSlopes().add(slopes.get("spiFisSlope"));
		lifts.get("clunas").getSlopes().add(slopes.get("clunasSlope"));
		lifts.get("motDaRi").getSlopes().add(slopes.get("valRuchnaSlope"));
		lifts.get("motDaRi").getSlopes().add(slopes.get("costeraFisSlope"));
		lifts.get("motDaRi").getSlopes().add(slopes.get("crapAlbFisSlope"));
		lifts.get("motDaRi").getSlopes().add(slopes.get("eraSlope"));
		lifts.get("motDaRi").getSlopes().add(slopes.get("motDaRiSlope"));
		lifts.get("salaniva").getSlopes().add(slopes.get("viaChampatschSlope"));
		lifts.get("salaniva").getSlopes().add(slopes.get("siertSlope"));
		lifts.get("salaniva").getSlopes().add(slopes.get("traumpisteSlope"));
		lifts.get("champatch1").getSlopes().add(slopes.get("pizNairSlope"));
		lifts.get("champatch1").getSlopes().add(slopes.get("champatschSlope"));
		lifts.get("champatch1").getSlopes().add(slopes.get("isolaPersaSlope"));
		lifts.get("champatch2").getSlopes().add(slopes.get("pizNairSlope"));
		lifts.get("champatch2").getSlopes().add(slopes.get("champatschSlope"));
		lifts.get("champatch2").getSlopes().add(slopes.get("isolaPersaSlope"));
	}
	
	public void linkLiftsAndGastronomies(Map<String, Lift> lifts, Map<String, Gastronomy> gastronomies) {
		//TODO review
		lifts.get("ftan").getGastronomies().add(gastronomies.get("prumanPrui"));
		lifts.get("ftan").getGastronomies().add(gastronomies.get("barLaCana"));
		liftRepository.save(lifts.get("ftan"));
		lifts.get("naluns").getGastronomies().add(gastronomies.get("laPalmaBar"));
		lifts.get("naluns").getGastronomies().add(gastronomies.get("chammannaNalunsGastro"));
		lifts.get("naluns").getGastronomies().add(gastronomies.get("laMotta"));
		lifts.get("naluns").getGastronomies().add(gastronomies.get("laCharpenna"));
		liftRepository.save(lifts.get("naluns"));
		lifts.get("rachogna").getGastronomies().add(gastronomies.get("barMarMotta"));
		lifts.get("rachogna").getGastronomies().add(gastronomies.get("vivaBar"));
		liftRepository.save(lifts.get("rachogna"));
		lifts.get("mottaNaluns").getGastronomies().add(gastronomies.get("barMarMotta"));
		lifts.get("mottaNaluns").getGastronomies().add(gastronomies.get("vivaBar"));
		lifts.get("mottaNaluns").getGastronomies().add(gastronomies.get("laMotta"));
		lifts.get("mottaNaluns").getGastronomies().add(gastronomies.get("laCharpenna"));
		liftRepository.save(lifts.get("mottaNaluns"));
		lifts.get("clunas").getGastronomies().add(gastronomies.get("alpetta"));
		liftRepository.save(lifts.get("clunas"));
		lifts.get("motDaRi").getGastronomies().add(gastronomies.get("alpetta"));
		liftRepository.save(lifts.get("motDaRi"));
		lifts.get("salaniva").getGastronomies().add(gastronomies.get("alpetta"));
		liftRepository.save(lifts.get("salaniva"));		
	}	
}
