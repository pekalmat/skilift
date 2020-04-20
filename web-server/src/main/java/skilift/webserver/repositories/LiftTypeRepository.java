package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.LiftType;

public interface LiftTypeRepository extends JpaRepository<LiftType, Long> {

}
