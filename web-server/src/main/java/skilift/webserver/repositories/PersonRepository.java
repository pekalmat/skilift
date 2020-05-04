package skilift.webserver.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import skilift.webserver.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findAllByOrderByFirstName();
        
    Optional<Person> findByEmail(String email);
}
