package wad.valid;

import wad.domain.Book;

public class BookValidator extends Validator {

    public boolean validateBookInputs(Object genreId, Object authorId, Object name, 
                                    Object pages, Object year, Object description) {
        if (!isLong(genreId) || !isLong(authorId)
            || !(name instanceof String) || !isInteger(pages) 
            || !isInteger(year) || !(description instanceof String)) {
            return false;
        }
        return true;
    }
    
    public boolean validateBook(Book book) {
        if (book.getName().length() < 4 || book.getName().length() > 50
        ||book.getPages() < 1 || book.getPages() > 2500 || book.getYear() > 2017 
        || book.getDescription().length() < 1 || book.getDescription().length() > 2500) {
            return false;
        }
        return true;
    }
    
}