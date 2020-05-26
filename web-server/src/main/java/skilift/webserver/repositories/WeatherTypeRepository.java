package skilift.webserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.WeatherType;

public interface WeatherTypeRepository extends JpaRepository<WeatherType, Long> {

    Optional<WeatherType> findById(Long id);

	
}
