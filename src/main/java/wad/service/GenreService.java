package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.domain.Genre;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;
import wad.repository.ReservationRepository;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    @Transactional
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
    
    @Transactional
    public Genre findGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }
    
    @Transactional
    public void saveGenre(String name) {
        genreRepository.save(new Genre(name));
    }
    
    @Transactional
    public void deleteGenre(Long genreId) {
        Genre g = genreRepository.findById(genreId);
        for (Book b : bookRepository.findByGenre(g)) {
            bookService.deleteBook(b.getId());
        }
        genreRepository.delete(g);
    }
}
