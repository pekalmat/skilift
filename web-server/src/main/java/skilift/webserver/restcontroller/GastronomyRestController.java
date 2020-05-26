package skilift.webserver.restcontroller;

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
import skilift.webserver.repositories.GastronomyRepository;
import skilift.webserver.utilization.UtilizationSummary;
import skilift.webserver.utilization.UtilizationSummaryFactory;

@RestController
public class GastronomyRestController {

	@Autowired
	private GastronomyRepository gastronomyRepository;
	@Autowired
	private UtilizationSummaryFactory utilizationSummaryFactory;
	
	@RequestMapping(value = "/skiapp/gastronomies", method = RequestMethod.GET)
    public ResponseEntity<List<Gastronomy>> getAllLifts(){
        List<Gastronomy> gastronomies = gastronomyRepository.findAll();
        if(gastronomies != null && !gastronomies.isEmpty()){
            return new ResponseEntity<List<Gastronomy>>(gastronomies, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Gastronomy>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/skiapp/gastronomy/{gastronomyId}", method = RequestMethod.GET)
    public ResponseEntity<Gastronomy> getLift(@PathVariable String gastronomyId){
        Optional<Gastronomy> gastronomy = gastronomyRepository.findById(new Long(gastronomyId));
        if(gastronomy.isPresent()) {
            return new ResponseEntity<Gastronomy>(gastronomy.get(), CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Gastronomy>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }
    
    @RequestMapping(value = "/skiapp/gastronomies/{gastronomyId}/utilization", method = RequestMethod.GET)
    public ResponseEntity<UtilizationSummary> getLiftUtilizationData(@PathVariable String gastronomyId){
        Optional<Gastronomy> gastronomy = gastronomyRepository.findById(new Long(gastronomyId));
        if(gastronomy.isPresent()) {
        	UtilizationSummary utilizationData = utilizationSummaryFactory.createUtilizationSummaryForGastronomy(gastronomy.get());
        	if(utilizationData.getUtilizationSummaryPerHour().isEmpty()) {
                return new ResponseEntity<UtilizationSummary>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NO_CONTENT);
        	}
        	return new ResponseEntity<UtilizationSummary>(utilizationData, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<UtilizationSummary>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }        
    }
}
