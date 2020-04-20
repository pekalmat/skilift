package skilift.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
