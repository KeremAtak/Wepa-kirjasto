package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Book;
import wad.domain.Reservation;
import wad.domain.Person;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(Long id);
    Reservation findByBook(Book book);
    List<Reservation> findByPerson(Person person);
}

