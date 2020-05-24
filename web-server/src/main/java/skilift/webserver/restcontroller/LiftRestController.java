package skilift.webserver.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import skilift.webserver.cors.CorsHeaderData;
import skilift.webserver.entities.Lift;
import skilift.webserver.repositories.LiftRepository;

@RestController
public class LiftRestController {

	@Autowired
    private LiftRepository liftRepository;
	
	/**
     * REST-Ressource für URL /skiapp/lifts (GET)
     * @return HTTP-Response mit einem Status 200 oder 404, sowie im ersten Fall einer Liste aller Lift-Entitäten im JSON-Format
     */
    @RequestMapping(value = "/skiapp/lifts", method = RequestMethod.GET)
    public ResponseEntity<List<Lift>> getAllLifts(){
        List<Lift> lifts = liftRepository.findAll();
        if(lifts != null && !lifts.isEmpty()){
            return new ResponseEntity<List<Lift>>(lifts, CorsHeaderData.getCorsHeaderData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Lift>>(CorsHeaderData.getCorsHeaderData(), HttpStatus.NOT_FOUND);
        }
    }

}
