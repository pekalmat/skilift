package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Lift;

public interface LiftRepository extends JpaRepository<Lift, Long>{

}
