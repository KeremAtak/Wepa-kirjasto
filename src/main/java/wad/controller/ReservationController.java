package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.domain.Reservation;
import wad.domain.Person;
import wad.service.PersonService;
import wad.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private ReservationService reservationService;
    
    //palauttaa yksittäisen henkilön varauksien näkymän tai palauttaa indeksin näkymän
    //jos polussa olevan henkilön tunnus ei vastaa kirjautuneen käyttäjän tunnusta
    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    public String viewReservations(@PathVariable("personId") Long personId, Model model) {
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
    
    //poistaa yksittäisen varauksen tämän tunnisteen perusteella
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