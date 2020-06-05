package skilift.webserver.restcontroller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import skilift.webserver.cors.CorsHeaderData;
import skilift.webserver.entities.Reservation;
import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Person;
import skilift.webserver.entities.SeatType;
import skilift.webserver.repositories.ReservationRepository;
import skilift.webserver.repositories.SeatTypeRepository;
import skilift.webserver.repositories.GastronomyRepository;
import skilift.webserver.repositories.PersonRepository;

@RestController
public class ReservationRestController {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private SeatTypeRepository seatTypeRepository;
	
	@Autowired
	private GastronomyRepository gastronomyRepository;
	
	@Autowired
	private PersonRepository personRepository;
		

	@RequestMapping(value = "/skiapp/reservation/new", method=RequestMethod.POST, produces={"application/json; charset=UTF-8", MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody ResponseEntity<?> createReservation(@RequestParam Map<String, String> name) throws JsonParseException, JsonMappingException, IOException {
		Reservation reservation = new Reservation();
		for(String key :  name.keySet()) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> map = mapper.readValue(key, Map.class);
			Optional<Person> person = personRepository.findByEmail(map.get("email"));
			if(person.isPresent()) {
				reservation.setPerson(person.get());
			} else {
				Person newPerson = new Person(map.get("vorname"), map.get("nachname"), map.get("email"), "noPassword", null);
				personRepository.save(newPerson);
				reservation.setPerson(newPerson);
			}
			String zeit = map.get("zeit");
			String[] zeitArray = zeit.split(":");
			Date reservationTime = new Date();
			reservationTime.setHours(Integer.parseInt(zeitArray[0]));
			reservationTime.setMinutes(Integer.parseInt(zeitArray[1]));
			reservation.setReservationTime(reservationTime);
			reservation.setReservationTimestamp(new Date());
			reservation.setSeats(Integer.parseInt(map.get("anzPersonen")));
			SeatType seatType = Boolean.parseBoolean(map.get("innen")) ? seatTypeRepository.findBySeatType("Indoor") : seatTypeRepository.findBySeatType("Outdoor") ;
			reservation.setSeatType(seatType);
			Optional<Gastronomy> gastronomy = gastronomyRepository.findById(new Long(map.get("gastronomyId")));
			reservation.setGastronomy(gastronomy.get());
			reservationRepository.save(reservation);
			
		}		
		return new ResponseEntity<>(CorsHeaderData.getCorsHeaderData(), HttpStatus.CREATED);
    }	

}
