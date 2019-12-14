package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Slf4j
@Builder
@NoArgsConstructor
public class ReadingEntry extends EntityWithUUID implements Serializable {

    @OneToMany(mappedBy = "readingEntry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Authorship> authorships;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_genre"))
    @JsonBackReference
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    private Genre genre;

    @OneToMany(mappedBy = "readingEntry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<ReadingRecord> recordTrail = new ArrayList<>();

    @Column(unique = true)
    private String title;

    private String type;

    private String medium;

    private boolean favourite;

    //TODO enum or db table
    private String language;

    //TODO make this a DB entity
    private String publisher;

    private String edition;

    private Timestamp created_ts;

    public ReadingEntry(String title, String type, String medium, String language,
                        String publisher, String edition, Genre genre, Authorship... authors) {
        this.title = title;
        this.type = type;
        this.medium = medium;
        this.language = language;
        this.publisher = publisher;
        this.edition = edition;
        for(Authorship authorship: authors){
            authorship.setReadingEntry(this);
        }
        this.authorships = Arrays.stream(authors).collect(Collectors.toSet());
        this.genre = genre;
        //brand new reading entries have one ReadingRecord
        //FIXME these will need to be compared by insertion date
    }

    @Override
    public String toString() {
        return "ReadingEntry{" +
                "id=" + super.getId() +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", medium=" + medium +
                ", language='" + language + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", authorships: '" + authorships +'\''+
                ", genre: '"+ genre + '\''+
                '}';
    }
}
