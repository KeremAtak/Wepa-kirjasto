package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(Long id);

}

