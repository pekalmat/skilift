package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
