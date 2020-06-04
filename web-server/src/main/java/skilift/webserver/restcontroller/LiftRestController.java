package skilift.webserver.restcontroller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import skilift.webserver.cors.CorsHeaderData;
import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.Slope;
import skilift.webserver.repositories.LiftRepository;
import skilift.webserver.utilization.UtilizationSummary;
import skilift.webserver.utilization.UtilizationSummaryFactory;

@RestController
public class LiftRestController {

	@Autowired
    private LiftRepository liftRepository;
	@Autowired
	private UtilizationSummaryFactory utilizationSummaryFactory;
	
    @RequestMapping(value = "/skiapp/lifts", method = RequestMethod.GET)
    public ResponseEntity<List<Lift>> getAllLifts(){
        List<Lift> lifts = liftRepository.findAll();
        if(lifts != null && !lifts.isEmpty()){
            return new ResponseEntity<List<Lift>>(lifts, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Lift>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/skiapp/lifts/{liftId}", method = RequestMethod.GET)
    public ResponseEntity<Lift> getLift(@PathVariable String liftId){
        Optional<Lift> lift = liftRepository.findById(new Long(liftId));
        if(lift.isPresent()) {
            return new ResponseEntity<Lift>(lift.get(), CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Lift>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }
    
    @RequestMapping(value = "/skiapp/lifts/{liftId}/neighbourSlopes", method = RequestMethod.GET)
    public ResponseEntity<List<Slope>> getLiftNeighbourSlopes(@PathVariable String liftId){
        Optional<Lift> lift = liftRepository.findById(new Long(liftId));
        if(lift.isPresent()) {
        	List<Slope> neighbourSlopes = (List<Slope>)(Object) Arrays.asList(lift.get().getSlopes().toArray());
        	if(neighbourSlopes.isEmpty()) {
                return new ResponseEntity<List<Slope>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NO_CONTENT);
        	}
            return new ResponseEntity<List<Slope>>(neighbourSlopes, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Slope>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }
    
    @RequestMapping(value = "/skiapp/lifts/{liftId}/neighbourGastronomies", method = RequestMethod.GET)
    public ResponseEntity<List<Gastronomy>> getLiftNeighbourGastronomies(@PathVariable String liftId){
        Optional<Lift> lift = liftRepository.findById(new Long(liftId));
        if(lift.isPresent()) {
        	List<Gastronomy> neighbourGastronomies = (List<Gastronomy>)(Object) Arrays.asList(lift.get().getGastronomies().toArray());
        	if(neighbourGastronomies.isEmpty()) {
                return new ResponseEntity<List<Gastronomy>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NO_CONTENT);
        	}
        	return new ResponseEntity<List<Gastronomy>>(neighbourGastronomies, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Gastronomy>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }

    @RequestMapping(value = "/skiapp/lifts/{liftId}/utilization", method = RequestMethod.GET)
    public ResponseEntity<UtilizationSummary> getLiftUtilizationData(@PathVariable String liftId){
        Optional<Lift> lift = liftRepository.findById(new Long(liftId));
        if(lift.isPresent()) {
        	UtilizationSummary utilizationData = utilizationSummaryFactory.createUtilizationSummaryForLift(lift.get());
        	if(utilizationData.getUtilizationSummaryPerHour().isEmpty()) {
                return new ResponseEntity<UtilizationSummary>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NO_CONTENT);
        	}
        	return new ResponseEntity<UtilizationSummary>(utilizationData, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<UtilizationSummary>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }
    
}
