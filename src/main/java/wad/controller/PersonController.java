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
import wad.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewPersons(Model model) {
        model.addAttribute("persons", personService.findAllPersonsOrdered());
        return "persons";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{personId}", method = RequestMethod.DELETE)
    public String deletePerson(@PathVariable("personId") Long personId, Model model) {
        personService.deletePerson(personId);
        return "redirect:/persons/";
    }
    
}