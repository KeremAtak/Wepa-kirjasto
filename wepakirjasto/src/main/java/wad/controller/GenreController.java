package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.*;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.ReservationRepository;
import wad.repository.PersonRepository;

@Controller
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PersonRepository userRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genres";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addGenre(@RequestParam String name, Model model) {
        genreRepository.save(new Genre(name));
        return "redirect:/genres";
    }
}