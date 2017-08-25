package wad.valid;

import wad.domain.Author;

public class AuthorValidator extends Validator{
    
    //validoi ovatko kirjailijan syötteet merkkijonoja (eivät saa olla kokonaislukuja)
    public boolean validateAuthorInput(Object name, Object surname) {
        if (!(name instanceof String) || isInteger(name)
            || !(surname instanceof String) || isInteger(surname)) {
            return false;
        }
        return true;
    }
    
    //validoi että kirjailijan etu-ja sukunimi täyttävät pituuksien ehdot
    public boolean validateAuthor(Author author) {
        if (author.getName().length() < 1 || author.getName().length() > 30
            || author.getSurname().length() < 1 || author.getName().length() > 30) {
            return false;
        }
        return true;
    }
}