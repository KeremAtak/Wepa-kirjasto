package wad.service;

import java.util.List;
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
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }
    
    @Transactional
    public Person findPersonByUsername(String username) {
        return personRepository.findByUsername(username);
    }
    
    @Transactional
    public Person findPersonById(Long personId) {
        return personRepository.findById(personId);
    }
    
    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }
    
    @Transactional
    public void deletePerson(Long personId) {
        Person p = personRepository.findById(personId);
        for (Reservation r : reservationRepository.findByPerson(p)) {
            reservationService.deleteReservation(p.getId());
        }
        personRepository.delete(p);
    }
}