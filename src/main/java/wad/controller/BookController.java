package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.*;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.ReservationRepository;
import wad.repository.PersonRepository;
import wad.service.BookService;

@Controller
@RequestMapping("/genres/{genreId}")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewBooks(@PathVariable("genreId") Long genreId, Model model) {
        Genre genre = genreRepository.findById(genreId);
        List<Book> books = bookRepository.findByGenre(genre);
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        
        model.addAttribute("genre", genre);
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "genre";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addBook(@RequestParam String name, @RequestParam int pages, @RequestParam int year, 
            @RequestParam String description, @RequestParam Long genreId, @RequestParam Long authorId) {
        Genre genre = genreRepository.findById(genreId);
        Author author = authorRepository.findById(authorId);
        
        Book b = bookRepository.save(new Book(name, pages, year, description, genre, author));
        return "redirect:/genres/" + b.getGenre().getId() + "/" + b.getId();
    }
    
    @RequestMapping(value = "{bookId}", method = RequestMethod.GET)
    public String singleBook(@PathVariable("genreId") Long genreId, @PathVariable("bookId") Long bookId, Model model) {
        Book book = bookRepository.findById(bookId);
        Author author = book.getAuthor();
        Genre genre = genreRepository.findById(genreId);
        
        model.addAttribute("genre", genre);
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        
        return "book";
    }
    
    @RequestMapping(value = "{bookId}", method = RequestMethod.POST)
    public String addReservation(@PathVariable("bookId") Long bookId, Model model) {
        bookService.addReservation(bookId);
        return "redirect:/genres/{genreId}/{bookId}";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{bookId}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("bookId") Long bookId, Model model) {
        bookService.deleteBook(bookId);
        return "redirect:/genres/{genreId}";
    }
    
}