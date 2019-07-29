package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Authorship> authorship = new HashSet<>();

    private String name;

    private String bio;

    private String website;

    private String notes;

    public Author() {
    }

    public Author(String name, String bio, String website, String notes) {
        this.name = name;
        this.bio = bio;
        this.website = website;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
