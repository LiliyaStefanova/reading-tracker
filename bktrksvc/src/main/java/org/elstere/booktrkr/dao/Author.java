package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Builder
public class Author extends EntityWithUUID implements Serializable {

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Authorship> authorship;

    private String name;

    private String bio;

    private String website;

    private String notes;

    private Timestamp created_ts;

}
