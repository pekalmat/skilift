package skilift.webserver.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT r FROM Reservation r where r.gastronomy = ?1 and r.seatType.id = ?2 and r.reservationTime between ?3 and ?4")
	public List<Reservation> findByGastronomyIdAndSeatTypeBetweenStartAndEndTime(Gastronomy gastronomy, Long seatTypeId, Date fromDate, Date toDate);
}
