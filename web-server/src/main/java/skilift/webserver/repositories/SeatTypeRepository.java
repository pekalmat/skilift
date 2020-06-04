package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {

	public SeatType findBySeatType(String seatType);
	
}
