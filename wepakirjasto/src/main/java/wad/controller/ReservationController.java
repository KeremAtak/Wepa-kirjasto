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
import wad.domain.User;
import wad.repository.ReservationRepository;
import wad.repository.UserRepository;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String returnMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("teksti", "Tervetuloa valikkoon, ");
        model.addAttribute("currentUser", userRepository.findByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public String viewReservation(@PathVariable("userId") Long userId, Model model) {
        User user = userRepository.findById(userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", user);

        if (user.getUsername().equals(authentication.getName())) {
            List<Reservation> reservations = reservationRepository.findByUser(user);
            model.addAttribute("reservations", reservations);
            return "reservations";
        }
        model.addAttribute("user", userRepository.findByUsername(authentication.getName()));
        return "index";
    }
    
    @RequestMapping(value = "{reservationId}", method = RequestMethod.DELETE)
    public String deleteReservation(@PathVariable("reservationId") Long reservationId, Model model) {
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.getBook().setReservation(null);
        reservation.getUser().setReservation(null);
        reservationRepository.delete(reservationId);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        String id = user.getId().toString();
        return "redirect:/reservations/" + id;
    }
}