package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import wad.domain.*;
import wad.repository.*;
import wad.valid.PersonValidator;

@Service
public class PersonService {
    
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ReservationService reservationService;
    
    private PersonValidator personValidator = new PersonValidator();
    
    //palauttaa kaikki käyttäjät
    @Transactional
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }
    
    //palauttaa kaikki käyttäjät aakkosjärjestyksessä
    @Transactional
    public List<Person> findAllPersonsOrdered() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "username");
        Page<Person> personPage = personRepository.findAll(pageable);
        return personPage.getContent();
    }
    
    //palauttaa käyttäjän tunnuksen perusteella
    @Transactional
    public Person findPersonById(Long personId) {
        return personRepository.findById(personId);
    }
    
    //palauttaa käyttäjän käyttäjänimen perusteella
    @Transactional
    public Person findPersonByUsername(String username) {
        return personRepository.findByUsername(username);
    }
    
    //tallentaa käyttäjän tietokantaan
    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }
    
    //poistaa käyttäjän tietokannasta ja kaikki käyttäjän varaukset
    @Transactional
    public void deletePerson(Long personId) {
        Person p = personRepository.findById(personId);
        for (Reservation r : reservationRepository.findByPerson(p)) {
            reservationService.deleteReservation(r.getId());
        }
        personRepository.delete(p);
    }
    
    //palauttaa validoituiko rekisteröinnin syötteet
    public boolean validateRegistrationInput(String username, String password) {
        return personValidator.validateRegistration(username, password);
    }

    //palauttaa löytyikö käyttäjä tietokannasta
    public boolean validateRegistrationUniqueness(Person person) {
        if (findPersonByUsername(person.getUsername()) != null || person.getUsername().equals("admin")) {
            return false;
        }
        return true;
    }
}