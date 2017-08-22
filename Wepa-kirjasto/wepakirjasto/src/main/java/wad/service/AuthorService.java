package wad.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author a = authorRepository.findById(authorId);
        for (Book b : bookRepository.findByAuthor(a)) {
            bookService.deleteBook(b.getId());
        }
        authorRepository.delete(a);
    }
}
