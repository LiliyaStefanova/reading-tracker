package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonTypeName("genre")
@NoArgsConstructor
public class Genre extends EntityWithUUID implements Serializable {

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<ReadingEntry> readingEntries = new HashSet<>();

    private String name;

    private String category;

    private String description;

    private Timestamp created_ts;

    public Genre(String name, String category, String description){
        this.name = name;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + super.getId() +
                ", readings =" + readingEntries.stream().map(ReadingEntry::getId).collect(Collectors.toList()) +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
