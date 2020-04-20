package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.LiftStatus;

public interface LiftStatusRepository extends JpaRepository<LiftStatus, Long> {

}
