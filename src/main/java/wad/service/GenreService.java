package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import wad.domain.*;
import wad.repository.*;
import wad.valid.GenreValidator;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    private GenreValidator genreValidator = new GenreValidator();
    
    //palauttaa kaikki genret
    @Transactional
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
    
    //palauttaa kaikki genret aakkosjärjestyksessä
    @Transactional
    public List<Genre> findAllGenresOrdered() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "name");
        Page<Genre> genrePage = genreRepository.findAll(pageable);
        return genrePage.getContent();
    }
    
    //palauttaa genren tunnuksen mukaan
    @Transactional
    public Genre findGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }
    
    //palauttaa genren nimen mukaan
    @Transactional
    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name.toLowerCase());
    }
    
    //tallentaa genren tietokantaan
    @Transactional
    public void saveGenre(String name) {
        genreRepository.save(new Genre(name.toLowerCase()));
    }
   
    //poistaa genren tietokannasta sekä poistaa kaikki genreen liittyvät kirjat
    @Transactional
    public void deleteGenre(Long genreId) {
        Genre g = genreRepository.findById(genreId);
        for (Book b : bookRepository.findByGenre(g)) {
            bookService.deleteBook(b.getId());
        }
        genreRepository.delete(g);
    }
    
    //palauttaa validoituiko genren syöte
    public boolean validateGenreInput(Object name) {
        return genreValidator.validateGenreInput(name);
    }
    
    //palauttaa validoituiko genre
    public boolean validateGenre(Genre genre) {
        return genreValidator.validateGenre(genre);
    }
    
    //palauttaa löytyikö genre tietokannasta
    public boolean validateGenreUniqueness(Genre genre) {
        if (findGenreByName(genre.getName().toLowerCase()) != null) {
            return false;
        }
        return true;
    }
}
