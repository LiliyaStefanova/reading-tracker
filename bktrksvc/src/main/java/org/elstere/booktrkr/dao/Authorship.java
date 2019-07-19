package org.elstere.booktrkr.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Authorship implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private ReadingEntry readingEntry;

    @Id
    @ManyToOne
    @JoinColumn
    private Author author;

    public Authorship(Author author) {
        this.author = author;
    }

    public ReadingEntry getReadingEntry() {
        return readingEntry;
    }

    public void setReadingEntry(ReadingEntry readingEntry) {
        this.readingEntry = readingEntry;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorship that = (Authorship) o;
        return Objects.equals(readingEntry, that.readingEntry) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readingEntry.getTitle(), author.getName());
    }
}
