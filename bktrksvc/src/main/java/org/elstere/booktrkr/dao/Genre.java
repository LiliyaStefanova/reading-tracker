package org.elstere.booktrkr.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "genre")
    public Set<ReadingEntry> readingEntries;

    private String name;

    private String description;

    public Genre(String name, String description){
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public Set<ReadingEntry> getReadingEntries() {
        return readingEntries;
    }

    public void setReadingEntries(Set<ReadingEntry> readingEntries) {
        this.readingEntries = readingEntries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", readings =" + readingEntries +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
