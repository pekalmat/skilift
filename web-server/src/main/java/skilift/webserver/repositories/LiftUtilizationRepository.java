package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.LiftUtilization;

public interface LiftUtilizationRepository extends JpaRepository<LiftUtilization, Long> {

}
