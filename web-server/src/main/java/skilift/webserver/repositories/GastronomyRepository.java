package skilift.webserver.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Gastronomy;

public interface GastronomyRepository extends JpaRepository<Gastronomy, Long> {

	public Optional<Gastronomy> findById(Long id);
}
