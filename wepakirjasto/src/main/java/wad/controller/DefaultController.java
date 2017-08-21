package wad.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
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
import wad.domain.Person;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.PersonRepository;

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @PostConstruct
    public void init() {
        
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String goToDefault(Model model) {
        return "redirect:/";
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
    public String registerUser(@ModelAttribute Person person, Model model) {
        personRepository.save(person);
        return "login";
    }
}