package skilift.webserver.restcontroller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import skilift.webserver.cors.CorsHeaderData;
import skilift.webserver.entities.Reservation;
import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Person;
import skilift.webserver.entities.Reservation;
import skilift.webserver.entities.SeatType;
import skilift.webserver.repositories.ReservationRepository;
import skilift.webserver.repositories.SeatTypeRepository;
import skilift.webserver.repositories.GastronomyRepository;
import skilift.webserver.repositories.PersonRepository;
import skilift.webserver.utilization.UtilizationSummaryFactory;

@RestController
public class ReservationRestController {

	@Autowired
	private ReservationRepository ReservationRepository;
	
	@Autowired
	private PersonRepository PersonRepository;
	
	@Autowired
	private UtilizationSummaryFactory utilizationSummaryFactory;
	/*
	@PostMapping(value = "/skiapp/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
		
		LocalDate reservationTimestamp = LocalDate.now();
		gastronomyTypes.get("bar")
		
		
		Optional<Person> person = PersonRepository.findByEmail("kolleto1@students.zhaw.ch");
		Optional<Gastronomy> gastronomy = GastronomyRepository.findById();
		Optional<SeatType> seatType = SeatTypeRepository.findById();
		
		Reservation newReservation = new Reservation(person.get(), gastronomy.get(), seatType.get(), Date reservationTime, reservationTimestamp, 2);
		ReservationRepository.save(newReservation);
		
		//return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
		
    }	

	*/
}
