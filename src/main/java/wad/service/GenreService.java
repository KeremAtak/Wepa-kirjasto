package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wad.domain.Book;
import wad.domain.Genre;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;

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
    public List<Genre> findAllGenresOrdered() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "name");
        Page<Genre> genrePage = genreRepository.findAll(pageable);
        return genrePage.getContent();
    }
    
    @Transactional
    public Genre findGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }
    
    @Transactional
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
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
