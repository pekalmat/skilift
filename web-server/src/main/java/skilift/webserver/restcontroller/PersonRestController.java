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

@RestController
public class PersonRestController {
    
    @Autowired
    private PersonRepository personRepository;
    
    /**
     * REST-Ressource für URL /skiapp/persons/{email} (GET)
     * @param email			email einer Person
     * @return             	HTTP-Response mit einem Status 200 oder 404, sowie im ersten Fall eine zur Email passende Person-Entität als Body (automatisch als JSON serialisiert)
     */
    @RequestMapping(value = "/skiapp/persons/{email}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable String email){
        Optional<Person> person = personRepository.findByEmail(email);
        if(person.isPresent()) {
            return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }        
    }
    
    /**
     * REST-Ressource für URL /skiapp/persons (GET)
     * @return				HTTP-Response mit einem Status 200 oder 404, sowie im ersten Fall einer Liste aller Welten-Entitäten im JSON-Format
     */
    @RequestMapping(value = "/skiapp/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> worlds = personRepository.findAllByOrderByFirstName();
        if(worlds != null && !worlds.isEmpty()){
            return new ResponseEntity<List<Person>>(worlds, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Person>>(HttpStatus.NOT_FOUND);
        }
    }
}
