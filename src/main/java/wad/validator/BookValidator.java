package wad.validator;

import wad.domain.Book;

public class BookValidator {

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
    
    private static boolean isInteger(Object obj) {
        try {
            String input = (String)obj;
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    private static boolean isLong(Object obj) {
        try {
            String input = (String)obj;
            Long l = Long.parseLong(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
