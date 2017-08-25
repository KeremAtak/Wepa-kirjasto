package wad.valid;

import wad.domain.Genre;

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
