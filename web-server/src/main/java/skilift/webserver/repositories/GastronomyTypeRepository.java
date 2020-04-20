package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.GastronomyType;

public interface GastronomyTypeRepository extends JpaRepository<GastronomyType, Long> {

}
