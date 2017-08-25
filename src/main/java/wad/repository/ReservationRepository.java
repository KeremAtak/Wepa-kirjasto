package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.*;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(Long id);
    Reservation findByBook(Book book);
    List<Reservation> findByPerson(Person person);
}

