package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.domain.Person;
import wad.service.PersonService;

@Controller
@RequestMapping("*")
public class DefaultController {
    
    @Autowired
    private PersonService personService;
    
    //ohjaa takaisin indeksiin jos polkua ei ole olemassa
    @RequestMapping(method = RequestMethod.GET)
    public String goToIndex() {
        return "redirect:/";
    }
    
    //palauttaa näkymään kirjautumissivun
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String viewLogin() {
        return "login";
    }

    //palauttaa näkymään rekisteröimissivun
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String viewRegistration() {
        return "register";
    }
    
    //palauttaa näkymään rekisteröimissivun, käyttäjä on kirjautunut käyttäjä
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("person", personService.findPersonByUsername(authentication.getName()));
        
        return "index";
    }
    
    //palauttaa virheviestin näkymän missä viesti on talletettuna modeliin
    @RequestMapping(value = "/errorpage", method = RequestMethod.GET) 
    public String viewErrorpage(@ModelAttribute("text") String text, Model model) {
        model.addAttribute("text", text);
        return "errorpage";
    }
    
    //Lisää käyttäjän tai palauttaa näkymässä virheviestin
    @RequestMapping(value = "register", method = RequestMethod.POST) 
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        
         if (!personService.validateRegistrationInput(username, password)) {
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