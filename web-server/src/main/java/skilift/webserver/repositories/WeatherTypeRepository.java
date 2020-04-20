package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.WeatherType;

public interface WeatherTypeRepository extends JpaRepository<WeatherType, Long> {

}
