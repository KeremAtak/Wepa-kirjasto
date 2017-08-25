package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.domain.*;
import wad.service.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private GenreService genreService;
    
    //Palauttaa näkymän missä genret ovat aakkosjärjestyksessä.
    @RequestMapping(method = RequestMethod.GET)
    public String viewGenres(Model model) {
        model.addAttribute("genres", genreService.findAllGenresOrdered());
        return "genres";
    }
    
    //Lisää genren tai palauttaa näkymässä virheviestin.
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addGenre(@RequestParam String name, Model model) { 
        if (!genreService.validateGenreInput(name)) {
            model.addAttribute("text", "Virhe genren luonnissa. Olihan syöte varmasti merkkijono?");
            return "errorpage";
        }
        Genre genre = new Genre(name);
        if (!genreService.validateGenreUniqueness(genre)) {
            model.addAttribute("text", "Virhe genren luonnissa. Genre on jo olemassa.");
            return "errorpage";
        }
        
        if (!genreService.validateGenre(genre)) {
            model.addAttribute("text", "Virhe genren luonnissa. Tarkasta nimen pituus.");
            return "errorpage";
        }
        genreService.saveGenre(name);
        return "redirect:/genres";
    }
    
    //Poistaa yksittäisen genren tämän tunnisteen perusteella
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{genreId}", method = RequestMethod.DELETE)
    public String deleteGenre(@PathVariable("genreId") Long genreId, Model model) {
        genreService.deleteGenre(genreId);
        return "redirect:/genres";
    }
}