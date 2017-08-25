package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Genre extends AbstractPersistable<Long> {
    
    private String name;
   
    @OneToMany
    private List<Book> books;
    
    public Genre(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
    
    public Genre() {
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book book) {
        this.books.add(book);
    }
    
    public void removeBook(Book book) {
        this.books.remove(book);
    }
}