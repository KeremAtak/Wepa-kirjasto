package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    //Palauttaa näkymässä käyttäjät aakkosjärjestyksessä sukunimen perusteella
    @RequestMapping(method = RequestMethod.GET)
    public String viewPersons(Model model) {
        model.addAttribute("persons", personService.findAllPersonsOrdered());
        return "persons";
    }
    
    //Poistaa yksittäisen käyttäjän tämän tunnisteen perusteella
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{personId}", method = RequestMethod.DELETE)
    public String deletePerson(@PathVariable("personId") Long personId, Model model) {
        personService.deletePerson(personId);
        return "redirect:/persons/";
    }
    
}