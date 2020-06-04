package skilift.webserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftUtilization;

public interface LiftUtilizationRepository extends JpaRepository<LiftUtilization, Long> {

	public List<LiftUtilization> findAllByLift(Lift lift);
	
}
