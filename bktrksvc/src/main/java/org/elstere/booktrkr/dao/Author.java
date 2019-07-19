package org.elstere.booktrkr.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Authorship> authorship = new HashSet<>();

    private String name;

    private String bio;

    private String website;

    private String notes;

    public Author(String name, String bio, String website, String notes) {
        this.name = name;
        this.bio = bio;
        this.website = website;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }


    public Set<Authorship> getAuthorship() {
        return authorship;
    }

    public void setAuthorship(Set<Authorship> authorship) {
        this.authorship = authorship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
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
