package skilift.webserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Gastronomy;

public interface GastronomyRepository extends JpaRepository<Gastronomy, Long> {

	
}
