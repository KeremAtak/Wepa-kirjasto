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
import wad.service.PersonService;

@Controller
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String goToDefault() {
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
    
    @RequestMapping(value = "register", method = RequestMethod.POST) 
    public String registerUser(@ModelAttribute Person person, Model model) {
        personService.savePerson(person);
        return "login";
    }
    
    @RequestMapping(value = "/errorpage", method = RequestMethod.GET) 
    public String viewErrorpage(@ModelAttribute("text") String text, Model model) {
        model.addAttribute("text", text);
        return "errorpage";
    }
}