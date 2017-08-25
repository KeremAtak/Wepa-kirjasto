package wad.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Book extends AbstractPersistable<Long> {
    
    private String title;
    private int pages;
    private int year;
    
    private String description;
    
    @OneToOne
    private Reservation reservation;
    
    @ManyToOne
    private Author author;
    
    @ManyToOne
    private Genre genre;
    
    public Book(String title, int pages, int year, String description, Genre genre, Author author) {
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.author = author;
        this.reservation = null;
    }
    
    public Book() {
         
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPages() {
        return pages;
    }
    
    public void setPages(int pages) {
        this.pages = pages;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
}