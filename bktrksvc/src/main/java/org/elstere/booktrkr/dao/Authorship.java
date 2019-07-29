package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class Authorship implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="reading_entry_id")
    @JsonBackReference
    private ReadingEntry readingEntry;

    @Id
    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonBackReference
    private Author author;

    public Authorship() {
    }

    public Authorship(Author author) {
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
