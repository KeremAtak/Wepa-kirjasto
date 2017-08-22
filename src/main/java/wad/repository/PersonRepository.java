package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findById(Long id);
    Person findByUsername(String username);
}
