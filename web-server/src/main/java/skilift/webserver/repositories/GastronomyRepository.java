package skilift.webserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.Person;

public interface GastronomyRepository extends JpaRepository<Gastronomy, Long> {

	
}
