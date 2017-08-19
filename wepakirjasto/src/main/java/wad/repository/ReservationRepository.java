package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Reservation;
import wad.domain.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(Long id);
    List<Reservation> findByUser(User user);
}

