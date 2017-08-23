package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Book;
import wad.domain.Reservation;
import wad.domain.Person;
import wad.repository.ReservationRepository;
import wad.repository.PersonRepository;
import wad.service.PersonService;
import wad.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private ReservationService reservationService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String returnMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", personService.findPersonByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    public String viewReservation(@PathVariable("personId") Long personId, Model model) {
        Person person = personService.findPersonById(personId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (person.getUsername().equals(authentication.getName())) {
            List<Reservation> reservations = reservationService.findReservationsByPerson(person);
            model.addAttribute("person", person);
            model.addAttribute("reservations", reservations);
            return "reservations";
        }
        model.addAttribute("person", personService.findPersonByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{reservationId}", method = RequestMethod.DELETE)
    public String deleteReservation(@PathVariable("reservationId") Long reservationId, Model model) {
        reservationService.deleteReservation(reservationId);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByUsername(authentication.getName());
        String id = person.getId().toString();
        model.addAttribute("person", person);
        return "redirect:/reservations/" + id;
    }
}