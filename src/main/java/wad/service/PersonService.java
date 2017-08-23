package wad.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.domain.Genre;
import wad.domain.Person;
import wad.domain.Reservation;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.PersonRepository;
import wad.repository.ReservationRepository;

@Service
public class PersonService {
    
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ReservationService reservationService;
    
    @Transactional
    public void deletePerson(Long personId) {
        Person p = personRepository.findById(personId);
        for (Reservation r : reservationRepository.findByPerson(p)) {
            reservationService.deleteReservation(p.getId());
        }
        personRepository.delete(p);
    }
}