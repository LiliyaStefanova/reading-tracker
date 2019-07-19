package org.elstere.booktrkr.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ReadingEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "readingEntry", cascade = CascadeType.ALL)
    private Set<Authorship> authors;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="GENRE_ID")
    private Genre genre;

    @Column(unique = true)
    private String title;

    private String type;

    private String medium;

    //TODO enum or db table
    private String language;

    //TODO make this a DB entity
    private String publisher;

    private String edition;

    public ReadingEntry(String title, String type, String medium, String language, String publisher, String edition, Authorship... authors) {
        this.title = title;
        this.type = type;
        this.medium = medium;
        this.language = language;
        this.publisher = publisher;
        this.edition = edition;
        for(Authorship authorship: authors){
            authorship.setReadingEntry(this);
        }
        this.authors = Arrays.stream(authors).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public Set<Authorship> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Authorship> authors){
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "ReadingEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", medium=" + medium +
                ", language='" + language + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", authors: '" + authors+'\''+
                '}';
    }
}
