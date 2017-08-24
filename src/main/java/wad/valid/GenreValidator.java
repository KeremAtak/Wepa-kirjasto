package wad.valid;

import org.springframework.beans.factory.annotation.Autowired;
import wad.domain.Genre;
import wad.service.GenreService;

public class GenreValidator extends Validator{
    
    public boolean validateGenreInput(Object name) {
        if (!(name instanceof String) || isInteger(name)) {
            return false;
        }
        return true;
    }
    
    public boolean validateGenre(Genre genre) {
        if (genre.getName().length() < 4 || genre.getName().length() > 20) {
            return false;
        }
        return true;
    }
}
