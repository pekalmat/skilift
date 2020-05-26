package skilift.webserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.GastronomyUtilization;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftUtilization;

public interface GastronomyUtilizationRepository extends JpaRepository<GastronomyUtilization, Long> {

	public List<GastronomyUtilization> findAllByGastronomy(Gastronomy lift);

}
