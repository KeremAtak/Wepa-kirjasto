package wad.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.*;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.ReservationRepository;
import wad.repository.PersonRepository;
import wad.service.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private GenreService genreService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewGenres(Model model) {
        model.addAttribute("genres", genreService.findAllGenresOrdered());
        return "genres";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addGenre(@Valid @ModelAttribute Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "errorpage";
        }
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }
    
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{genreId}", method = RequestMethod.DELETE)
    public String deleteGenre(@PathVariable("genreId") Long genreId, Model model) {
        genreService.deleteGenre(genreId);
        return "redirect:/genres";
    }
}