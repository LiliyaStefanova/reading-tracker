package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ReadingRecord extends EntityWithUUID implements Serializable {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reading_entry"))
    @JsonBackReference
    private ReadingEntry readingEntry;

    private String status;

    private double percentageComplete;

    private Date startDate;

    private Date endDate;

    private double completionTime;

    private String notes;

    private Timestamp created_ts;

    public ReadingRecord(String status, double percentageComplete, Date startDate, Date endDate,
                         double completionTime, String notes, ReadingEntry entry) {
        this.status = status;
        this.percentageComplete = percentageComplete;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completionTime = completionTime;
        this.notes = notes;
        this.readingEntry = entry;
    }


    @Override
    public String toString() {
        return "ReadingRecord{" +
                "id=" + super.getId() +
                ", status='" + status + '\'' +
                ", percentageComplete=" + percentageComplete +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completionTime=" + completionTime +
                ", notes='" + notes + '\'' +
                '}';
    }

}
