package org.elstere.reading.tracker.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.api.entities.outbound.ReadingRecordOutbound;
import org.elstere.reading.tracker.service.ReadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class ReadingRecordController {

    private final ReadingRecordService readingRecordService;

    @Autowired
    public ReadingRecordController(ReadingRecordService readingRecordService) {
        this.readingRecordService = readingRecordService;
    }

    @GetMapping("/readingRecord/all")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<List<ReadingRecordOutbound>> fetchAllReadingRecords() {
        List<ReadingRecordOutbound> records = this.readingRecordService.getAllReadingRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/readingRecord/{id}")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<ReadingRecordOutbound> getReadingRecordById(@PathVariable UUID id){

        Optional<ReadingRecordOutbound> maybeReadingRecord = this.readingRecordService.getReadingRecordById(id);
        return ResponseEntity.of(maybeReadingRecord);

    }


}
