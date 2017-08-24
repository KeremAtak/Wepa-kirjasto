package wad.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(Long id);
    List<Author> findAuthorByName(String name);
}
