package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends EntityWithUUID implements Serializable {

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Authorship> authorship = new HashSet<>();

    private String name;

    private String bio;

    private String website;

    private String notes;

    private Timestamp created_ts;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
