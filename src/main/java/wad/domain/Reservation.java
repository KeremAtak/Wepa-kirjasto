package wad.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Reservation extends AbstractPersistable<Long> {
    
    @OneToOne
    private Person person;
    
    @OneToOne
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