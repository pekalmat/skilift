package skilift.webserver.seeddata.creators;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Person;
import skilift.webserver.entities.Reservation;
import skilift.webserver.entities.SeatType;
import skilift.webserver.repositories.PersonRepository;
import skilift.webserver.repositories.ReservationRepository;
import skilift.webserver.repositories.SeatTypeRepository;

@Component
public class PersonReservationSeedDataCreator {

	private PersonRepository personRepository;
	private SeatTypeRepository seatTypeRepository;
	private ReservationRepository reservationRepository;
	
	@Autowired
	public PersonReservationSeedDataCreator(PersonRepository personRepository, SeatTypeRepository seatTypeRepository, ReservationRepository reservationRepository) {
		this.personRepository = personRepository;
		this.seatTypeRepository = seatTypeRepository;
		this.reservationRepository = reservationRepository;
	}
	
	public void deleteAll() {
		reservationRepository.deleteAll();
		seatTypeRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	public Map<String, Person> createPersons() {
		Person mati = new Person("Mateusz", "Pekalski", "pekalmat@students.zhaw.ch", "pass123", null);
		personRepository.save(mati);
		Person tobias = new Person("Tobias", "Koller", "kolleto@students.zhaw.ch", "pass123", null);
		personRepository.save(tobias);
		Person gianin = new Person("Gianin", "Steinegger", "steingia@students.zhaw.ch", "pass123", 213216);
		personRepository.save(gianin);
		Map<String, Person> persons = new HashMap<>();
		persons.put("mati", mati);
		persons.put("tobias", tobias);
		persons.put("gianin", gianin);
		return persons;
	}
	
	public Map<String, SeatType> createSeatTypes() {
		SeatType indoor = new SeatType("Indoor");
		seatTypeRepository.save(indoor);
		SeatType outdoor = new SeatType("Outdoor");
		seatTypeRepository.save(outdoor);
		Map<String, SeatType> seatTypes = new HashMap<>();
		seatTypes.put("indoor", indoor);
		seatTypes.put("outdoor", outdoor);
		return seatTypes;
	}
	
	public Map<String, Reservation> createReservations(Map<String, Person> persons, Map<String, Gastronomy> gastronomies, Map<String, SeatType> seatTypes) {
		@SuppressWarnings("deprecation")
		Reservation reservationMati = new Reservation(persons.get("mati"), gastronomies.get("laMotta"), seatTypes.get("indoor"), new Date(2020,4,20,13,30), new Date(2020,4,20,13,0), 2);
		reservationRepository.save(reservationMati);
		@SuppressWarnings("deprecation")
		Reservation reservationTobi = new Reservation(persons.get("tobias"), gastronomies.get("alpetta"), seatTypes.get("indoor"), new Date(2020,4,20,13,30), new Date(2020,4,20,13,0), 4);
		reservationRepository.save(reservationTobi);
		@SuppressWarnings("deprecation")
		Reservation reservationGianin = new Reservation(persons.get("gianin"), gastronomies.get("laCharpenna"), seatTypes.get("outdoor"), new Date(2020,4,20,13,30), new Date(2020,4,20,13,0), 3);
		reservationRepository.save(reservationGianin);
		Map<String, Reservation> reservations = new HashMap<>();
		reservations.put("reservationMati", reservationMati);
		reservations.put("reservationTobi", reservationTobi);
		reservations.put("reservationGianin", reservationGianin);
		return reservations;
	}
	
}