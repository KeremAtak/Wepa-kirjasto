package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.*;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(Long id);
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre genre);
}

