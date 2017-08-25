package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.repository.*;
import wad.valid.AuthorValidator;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    private AuthorValidator authorValidator = new AuthorValidator();
    
    //palauttaa kaikki kirjailijat
    @Transactional
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    
    //palauttaa kaikki kirjailijat aakkosjärjestyksessä
    @Transactional
    public List<Author> findAllAuthorsOrdered() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "surname");
        Page<Author> authorPage = authorRepository.findAll(pageable);
        return authorPage.getContent();
    }
    
    //palauttaa kirjailijan tunnisteen perusteella
    @Transactional
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }
    
    //tallentaa kirjailijan tietokantaan
    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    
    //poistaa kirjailijan tietokannasta ja kaikki kirjailijan kirjat
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author a = authorRepository.findById(authorId);
        for (Book b : bookRepository.findByAuthor(a)) {
            bookService.deleteBook(b.getId());
        }
        authorRepository.delete(a);
    }
    
    
    //palauttaa validoituvaitko kirjailijan syötteet
    public boolean validateAuthorInput(Object name, Object surname) {
        return authorValidator.validateAuthorInput(name, surname);
    }
    
    //palauttaa validoituiko kirjailija
    public boolean validateAuthor(Author author) {
        return authorValidator.validateAuthor(author);
    }
}
 