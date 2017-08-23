package wad.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(Long id);
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre genre);
}

