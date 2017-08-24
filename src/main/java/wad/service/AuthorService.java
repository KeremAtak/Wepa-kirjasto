package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
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
    
    @Transactional
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    
    @Transactional
    public List<Author> findAllAuthorsOrdered() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "surname");
        Page<Author> authorPage = authorRepository.findAll(pageable);
        return authorPage.getContent();
    }
    
    @Transactional
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }
    
    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author a = authorRepository.findById(authorId);
        for (Book b : bookRepository.findByAuthor(a)) {
            bookService.deleteBook(b.getId());
        }
        authorRepository.delete(a);
    }
    
    public boolean validateAuthorInput(Object name, Object surname) {
        return authorValidator.validateAuthorInput(name, surname);
    }
    
    public boolean validateAuthor(Author author) {
        return authorValidator.validateAuthor(author);
    }
}
 