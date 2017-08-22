package wad.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.domain.Person;
import wad.domain.Reservation;
import wad.repository.BookRepository;
import wad.repository.PersonRepository;
import wad.repository.ReservationRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Transactional
    public void addReservation(Long bookId) {
        Book book = bookRepository.findById(bookId);
        
        if (book.getReservation() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Person person = personRepository.findByUsername(authentication.getName());

            Reservation reservation = new Reservation(person, bookRepository.findById(bookId));
            book.setReservation(reservation);
            person.setReservation(reservation);

            reservationRepository.save(reservation);
            personRepository.save(person);
        }
    }
    
    @Transactional
    public void deleteBook(Long bookId) {
        Book b = bookRepository.findById(bookId);
        b.setAuthor(null);
        b.setGenre(null);
        if (b.getReservation() != null) {
            reservationRepository.delete(reservationRepository.findByBook(bookId));
        }
        
        bookRepository.delete(bookId);
    }
}
