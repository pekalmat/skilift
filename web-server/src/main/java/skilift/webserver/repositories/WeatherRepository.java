package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
