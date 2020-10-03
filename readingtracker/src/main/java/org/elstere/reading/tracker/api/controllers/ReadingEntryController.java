package org.elstere.reading.tracker.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.api.entities.inbound.ReadingEntryInbound;
import org.elstere.reading.tracker.api.entities.outbound.ReadingEntryOutbound;
import org.elstere.reading.tracker.service.ReadingEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ReadingEntryController {

    private final ReadingEntryService service;

    @Autowired
    public ReadingEntryController(ReadingEntryService service){
        this.service = service;
    }

    @GetMapping("/readingEntry/all")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<List<ReadingEntryOutbound>> fetchReadingEntries(){
        List<ReadingEntryOutbound> entries = this.service.getAllReadingEntries();
        log.info(entries.get(0).toString());
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/readingEntry/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<ReadingEntryOutbound> fetchReadingEntryById(@PathVariable UUID id){
        return ResponseEntity.of(service.getReadingEntryById(id));
    }

    @GetMapping("/readingEntry")
    @CrossOrigin(origins="*")
    @ResponseBody
    public List<ReadingEntryOutbound> searchByTitle(@RequestParam("title") String title){
        return this.service.searchByTitle(title);
    }


    @PostMapping("/readingEntry")
    @CrossOrigin(origins="*")
    @ResponseBody
    public UUID insertReadingEntry(ReadingEntryInbound reading){
        ReadingEntryOutbound entry = this.service.insertEntry(reading);
        if(entry!=null){
            return entry.getId();
        } else{
            return new UUID(0,0);
        }
    }
}
