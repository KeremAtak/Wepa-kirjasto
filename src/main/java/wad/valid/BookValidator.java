package wad.valid;

import wad.domain.Book;

public class BookValidator extends Validator {

    //validoi ovatko tunnukset tyypiltään Long, nimi ja kuvaus merkkijonoja sekä vuosi ja sivut kokonaislukuja.
    public boolean validateBookInputs(Object genreId, Object authorId, Object title, 
                                    Object pages, Object year, Object description) {
        if (!isLong(genreId) || !isLong(authorId)
            || !(title instanceof String) || !isInteger(pages) 
            || !isInteger(year) || !(description instanceof String)) {
            return false;
        }
        return true;
    }
    
    //validoi täyttävätkö luodun kirjan syötteet pituuksien ehdot.
    public boolean validateBook(Book book) {
        if (book.getTitle().length() < 4 || book.getTitle().length() > 50
        ||book.getPages() < 1 || book.getPages() > 2500 || book.getYear() > 2017 
        || book.getDescription().length() < 1 || book.getDescription().length() > 2500) {
            return false;
        }
        return true;
    }
    
}
