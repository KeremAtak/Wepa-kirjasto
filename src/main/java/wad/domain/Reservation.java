package wad.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Reservation extends AbstractPersistable<Long> {
    
    private Person person;
    private Book book;
    
    public Reservation(Person person, Book book) {
        this.person = person;
        this.book = book;
    }
    
    public Reservation() {
        
    }
    
    public Person getUser() {
        return person;
    }
    
    public void setUser(Person person) {
        this.person = person;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
}