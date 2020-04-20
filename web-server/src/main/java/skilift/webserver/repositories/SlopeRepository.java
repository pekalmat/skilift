package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Slope;

public interface SlopeRepository extends JpaRepository<Slope, Long> {

}
