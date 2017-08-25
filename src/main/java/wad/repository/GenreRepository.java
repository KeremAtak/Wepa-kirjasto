package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(Long id);
    Genre findByName(String name);
}

