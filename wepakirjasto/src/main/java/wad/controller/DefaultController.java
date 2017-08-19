package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;
import wad.domain.User;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.UserRepository;

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @PostConstruct
    public void init() {
        
        User u1 = new User();
        u1.setUsername("user");
        u1.setPassword("password");
        User u2 = new User();
        u2.setUsername("admin");
        u2.setPassword("password");
        
        userRepository.save(u1);
        userRepository.save(u2);
        
        Author a1 = new Author("Erwin", "Rommel");
        Author a2 = new Author("Kyösti", "Kallio");
        
        authorRepository.save(a1);
        authorRepository.save(a2);
        
        Genre g1 = new Genre("Kauhu");
        Genre g2 = new Genre("Komedia");
        
        genreRepository.save(g1);
        genreRepository.save(g2);
        
        Book b1 = new Book("kirja1", 11, 1982, "desc1", g1, a1);
        Book b2 = new Book("kirja2", 12, 2011, "desc2", g2, a1);
        Book b3 = new Book("kirja3", 13, 2014, "desc3", g2, a2);
        
        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);
        
        a1.addBook(b1);
        a1.addBook(b2);
        a2.addBook(b3);
        
        g1.addBook(b1);
        g2.addBook(b2);
        g2.addBook(b3);
        
        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);
        
        genreRepository.save(g1);
        genreRepository.save(g2);
        
        authorRepository.save(a1);
        authorRepository.save(a2);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String goToLogin(Model model) {
        return "login";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String viewLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String viewRegistration(Model model) {
        return "register";
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST) 
    public String registerUser(@ModelAttribute User user, Model model) {
        userRepository.save(user);
        return "redirect:/login";
    }
}