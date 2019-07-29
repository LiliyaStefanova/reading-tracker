package org.elstere.booktrkr.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
public class ReadingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="reading_entry_id")
    @JsonBackReference
    private ReadingEntry readingEntry;

    private String status;

    private double percentageComplete;

    private Date startDate;

    private Date endDate;

    private double completionTime;

    private String notes;

    //TODO date and time stamp for all entities


    public ReadingRecord() {
    }

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

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ReadingRecord{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", percentageComplete=" + percentageComplete +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completionTime=" + completionTime +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingRecord record = (ReadingRecord) o;
        return id == record.id &&
                Double.compare(record.percentageComplete, percentageComplete) == 0 &&
                Double.compare(record.completionTime, completionTime) == 0 &&
                Objects.equals(status, record.status) &&
                Objects.equals(startDate, record.startDate) &&
                Objects.equals(endDate, record.endDate) &&
                Objects.equals(notes, record.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, percentageComplete, startDate, endDate, completionTime, notes);
    }
}
