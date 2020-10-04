package org.elstere.reading.tracker.service;

import org.elstere.reading.tracker.api.entities.outbound.ReadingRecordOutbound;
import org.elstere.reading.tracker.dao.ReadingRecord;
import org.elstere.reading.tracker.dao.repository.ReadingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReadingRecordService {

    private final ReadingRecordRepository readingRecordRepository;

    @Autowired
    public ReadingRecordService(ReadingRecordRepository readingRecordRepository){
        this.readingRecordRepository = readingRecordRepository;
    }

    public List<ReadingRecordOutbound> getAllReadingRecords(){
        return readingRecordRepository.findAll().stream()
                .map(this::generateOutboundPayload)
                .collect(Collectors.toList());
    }

    public Optional<ReadingRecordOutbound> getReadingRecordById(UUID id){
        Optional<ReadingRecord> maybeReadingRecord = this.readingRecordRepository.findById(id);
        return maybeReadingRecord.map(this::generateOutboundPayload);
    }


    private ReadingRecordOutbound generateOutboundPayload(ReadingRecord rr){
        return ReadingRecordOutbound.builder()
                .id(rr.getId())
                .book(rr.getReadingEntry().getTitle())
                .completionTime(rr.getCompletionTime())
                .percentageComplete(rr.getPercentageComplete())
                .startDate(rr.getStartDate())
                .endDate(rr.getEndDate())
                .status(rr.getStatus())
                .notes(rr.getNotes())
                .build();
    }

}


