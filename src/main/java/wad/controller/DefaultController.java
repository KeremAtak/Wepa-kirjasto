package wad.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;
import wad.domain.Person;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.PersonRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String goToIndex() {
        return "redirect:/";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String viewLogin() {
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String viewRegistration() {
        return "register";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("person", personService.findPersonByUsername(authentication.getName()));
        
        return "index";
    }
    
    @RequestMapping(value = "/errorpage", method = RequestMethod.GET) 
    public String viewErrorpage(@ModelAttribute("text") String text, Model model) {
        model.addAttribute("text", text);
        return "errorpage";
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST) 
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        
         if (!personService.validateRegistration(username, password)) {
            model.addAttribute("text", "Virhe käyttäjän luonnissa. Tarkista nimen ja salasanan pituudet.");
            return "errorpage";
        }
        
        Person person = new Person(username, password);
        if (!personService.validateRegistrationUniqueness(person)) {
            model.addAttribute("text", "Virhe käyttäjän luonnissa. Käyttäjä on jo olemassa tällä nimellä.");
            return "errorpage";
        }
        
        personService.savePerson(person);
        return "login";
    }
    
}