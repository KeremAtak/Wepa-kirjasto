package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;
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
    
    @Autowired
    private ReservationService reservationService;
    
    @Transactional
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }
    
    @Transactional
    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }
    
    @Transactional
    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }
    
    @Transactional
    public Book saveBook(String name, int pages, int year, String description, Genre genre, Author author) {
        return bookRepository.save(new Book(name, pages, year, description, genre, author));
    }
    
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
            reservationService.deleteReservation(b.getReservation().getId());
        }
        
        bookRepository.delete(bookId);
    }
}
