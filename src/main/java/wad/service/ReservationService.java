package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.*;
import wad.repository.*;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    //palauttaa varaukset käyttäjän mukaan
    @Transactional
    public List<Reservation> findReservationsByPerson(Person person) {
        return reservationRepository.findByPerson(person);
    }
    
    //poistaa varauksen tietokannasta sekä tämän yhteydet kirjaan ja käyttäjään
    @Transactional
    public void deleteReservation(Long reservationid) {
        Reservation reservation = reservationRepository.findById(reservationid);
        reservation.getBook().setReservation(null);
        reservation.getUser().setReservation(null);
        reservationRepository.delete(reservationid);
    }
}
