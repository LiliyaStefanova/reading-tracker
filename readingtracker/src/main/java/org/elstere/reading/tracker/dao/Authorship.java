package org.elstere.reading.tracker.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
public class Authorship extends EntityWithUUID implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name = "fk_reading_entry"))
    @JsonBackReference
    private ReadingEntry readingEntry;

    @Id
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name = "fk_author"))
    @JsonBackReference
    private Author author;

    private Timestamp created_ts;

    public Authorship(Author author) {
        this.author = author;
    }

}
