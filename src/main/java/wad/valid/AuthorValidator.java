package wad.valid;

import wad.domain.Author;

public class AuthorValidator extends Validator{
    
    public boolean validateAuthorInput(Object name, Object surname) {
        if (!(name instanceof String) || isInteger(name)
            || !(surname instanceof String) || isInteger(surname)) {
            return false;
        }
        return true;
    }
    
    public boolean validateAuthor(Author author) {
        if (author.getName().length() < 1 || author.getName().length() > 30
            || author.getSurname().length() < 1 || author.getName().length() > 30) {
            return false;
        }
        return true;
    }
}