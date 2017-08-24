package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.*;
import wad.service.AuthorService;
import wad.service.BookService;
import wad.service.GenreService;
import wad.valid.BookValidator;

@Controller
@RequestMapping("/genres/{genreId}")
public class BookController {
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;
        
    @Autowired
    private GenreService genreService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewBooks(@PathVariable("genreId") Long genreId, Model model) {
        if (authorService.findAllAuthors().isEmpty()) {
            return "redirect:/authors";
        }
        
        Genre genre = genreService.findGenreById(genreId);
        List<Book> books = bookService.findBooksByGenre(genre);
        List<Author> authors = authorService.findAllAuthorsOrdered();
        List<Genre> genres = genreService.findAllGenresOrdered();
        
        model.addAttribute("genre", genre);
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "genre";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String addBook(@PathVariable("genreId") String genreId, @RequestParam String name, @RequestParam String pages, 
            @RequestParam String year, @RequestParam String description, @RequestParam String authorId, Model model) {
        if (!bookService.validateBookInputs(genreId, authorId, name, pages, year, description)) {
            model.addAttribute("text", "Virhe kirjan luonnissa. Lisäsithän esimerkiksi sivut ja vuodet numeroina?");
            return "errorpage";
        }
        Genre genre = genreService.findGenreById(Long.parseLong(genreId));
        Author author = authorService.findAuthorById(Long.parseLong(authorId));
        
        Book b = new Book(name, Integer.parseInt(pages), Integer.parseInt(year), description, genre, author);
        
        if (!bookService.validateBook(b)) {
            model.addAttribute("text", "Virhe kirjan luonnissa. Tarkista että syötteiden ehdot täyttyvät.");
            return "errorpage";
        }
        bookService.saveBook(b);
        return "redirect:/genres/{genreId}/" + b.getId();
    }
    
    
    @RequestMapping(value = "{bookId}", method = RequestMethod.GET)
    public String singleBook(@PathVariable("genreId") Long genreId, @PathVariable("bookId") Long bookId, Model model) {
        Book book = bookService.findBookById(bookId);
        Author author = book.getAuthor();
        Genre genre = genreService.findGenreById(genreId);
        
        model.addAttribute("genre", genre);
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        
        if (book.getReservation() == null) {
            model.addAttribute("status", "varaa");
        } else {
            model.addAttribute("status", "varattu");
        }
        return "book";
    }
    
    @RequestMapping(value = "{bookId}", method = RequestMethod.POST)
    public String addReservation(@PathVariable("bookId") Long bookId, Model model) {
        bookService.addReservation(bookId);
        return "redirect:/genres/{genreId}/{bookId}";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{bookId}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("bookId") Long bookId, Model model) {
        bookService.deleteBook(bookId);
        return "redirect:/genres/{genreId}";
    }
    
}