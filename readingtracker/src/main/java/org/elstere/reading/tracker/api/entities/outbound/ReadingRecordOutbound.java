package org.elstere.reading.tracker.api.entities.outbound;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingRecordOutbound {

    private UUID id;

    private String book;

    private String status;

    private double percentageComplete;

    private Date startDate;

    private Date endDate;

    private double completionTime;

    private String notes;
}
