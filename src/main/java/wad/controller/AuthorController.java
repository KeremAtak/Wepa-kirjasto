package wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Person;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
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
    public String addAuthor(@Valid @ModelAttribute Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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