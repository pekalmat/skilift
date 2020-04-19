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

import skilift.webserver.entities.Person;
import skilift.webserver.repositories.PersonRepository;

/**
 * REST-Controller für die Ressource World
 */
@RestController
public class PersonRestController {
    
    // Verdrahten der Repository-Klasse, um Karten in der Datenbank zu finden
    @Autowired
    private PersonRepository personRepository;
    
    /**
     * REST-Ressource für URL /infmapi/v1/worlds/{name} (GET)
     * 
     * @param name        Name einer Welt
     * @return               HTTP-Response mit einem Status 200 oder 404, sowie im ersten Fall einer zur Id passenden Welt-Entität als Body (automatisch als JSON serialisiert)
     */
    @RequestMapping(value = "/infmapi/v1/worlds/{name}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable String name){        
        // Zur Id passende Welt suchen
        Optional<Person> world = personRepository.findByFirstName(name);
        
        // Falls Welt gefunden wurde, dann world zurück geben
        if(world.isPresent()) {
            return new ResponseEntity(world.get(), HttpStatus.OK);
        } else {
            // Ansonsten ResourceNotFoundException (404)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }        
    }
    
    /**
     * REST-Ressource für URL /infmapi/v1/worlds (GET)
     * 
     * @return                  HTTP-Response mit einem Status 200 oder 404, sowie im ersten Fall einer Liste aller Welten-Entitäten im JSON-Format
     */
    @RequestMapping(value = "/infmapi/v1/worlds", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons(){
        // Alle Karten aus dem Repository laden und der cards-Variable zuweisen
        List<Person> worlds = personRepository.findAllByOrderByFirstName();
        
        // Wenn die Liste Einträge enthält...
        if(worlds != null && !worlds.isEmpty()){
            // ... dann diese als Body zurückgeben
            return new ResponseEntity(worlds, HttpStatus.OK);
        } else {
            // ... ansonsten ResourceNotFoundException (404)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
