package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Author;
import wad.domain.Book;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.service.AuthorService;

@Controller
@RequestMapping("/authors")
@EnableGlobalMethodSecurity(securedEnabled = true)

public class AuthorController {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorService authorService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addAuthor(@RequestParam String name, @RequestParam String surname) {
        authorRepository.save(new Author(name, surname));
        return "redirect:/authors/";
    }
    
    @RequestMapping(value = "{authorId}", method = RequestMethod.GET)
    public String singleAuthor(@PathVariable("authorId") Long authorId, Model model) {
        Author author = authorRepository.findById(authorId);
        
        model.addAttribute("books", bookRepository.findByAuthor(author));
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