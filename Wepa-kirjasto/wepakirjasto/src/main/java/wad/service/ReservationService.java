package wad.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wad.domain.Person;
import wad.domain.Reservation;
import wad.repository.PersonRepository;
import wad.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Transactional
    public void deleteReservation(Long reservationid) {
        Reservation reservation = reservationRepository.findById(reservationid);
        reservation.getBook().setReservation(null);
        reservation.getUser().setReservation(null);
        reservationRepository.delete(reservationid);
    }
}
