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

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String returnMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("teksti", "Tervetuloa valikkoon, ");
        model.addAttribute("currentUser", personRepository.findByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public String viewReservation(@PathVariable("personId") Long personId, Model model) {
        Person person = personRepository.findById(personId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", person);

        if (person.getUsername().equals(authentication.getName())) {
            List<Reservation> reservations = reservationRepository.findByPerson(person);
            model.addAttribute("reservations", reservations);
            return "reservations";
        }
        model.addAttribute("user", personRepository.findByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{reservationId}", method = RequestMethod.DELETE)
    public String deleteReservation(@PathVariable("reservationId") Long reservationId, Model model) {
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.getBook().setReservation(null);
        reservation.getUser().setReservation(null);
        reservationRepository.delete(reservationId);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = personRepository.findByUsername(authentication.getName());
        model.addAttribute("user", person);
        String id = person.getId().toString();
        return "redirect:/reservations/" + id;
    }
}