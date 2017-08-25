package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wad.domain.Author;
import wad.service.AuthorService;
import wad.service.BookService;

@Controller
@RequestMapping("/authors")
    
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthorsOrdered());
        return "authors";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addAuthor(@RequestParam String name, @RequestParam String surname, Model model) {
        if (!authorService.validateAuthorInput(name, surname)) {
            model.addAttribute("text", "Virhe kirjailijan luonnissa. Olivathan syötteet varmasti merkkijonoja?");
            return "errorpage";
        }
        Author author = new Author(name, surname);
        if (!authorService.validateAuthor(author)) {
            model.addAttribute("text", "Virhe kirjailijan luonnissa. Tarkasta etu-ja sukunimen pituus");
            return "errorpage";
        }
        authorService.saveAuthor(author);
        return "redirect:/authors/";
    }
    
    @RequestMapping(value = "{authorId}", method = RequestMethod.GET)
    public String singleAuthor(@PathVariable("authorId") Long authorId, Model model) {
        Author author = authorService.findAuthorById(authorId);
        
        model.addAttribute("books", bookService.findBooksByAuthor(author));
        model.addAttribute("author", author);
        return "author";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{authorId}", method = RequestMethod.DELETE)
    public String deleteAuthor(@PathVariable("authorId") Long authorId, Model model) {
        authorService.deleteAuthor(authorId);
        return "redirect:/authors";
    }
}