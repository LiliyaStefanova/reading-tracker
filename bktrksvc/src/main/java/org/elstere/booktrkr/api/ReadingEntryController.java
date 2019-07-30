package org.elstere.booktrkr.api;

import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.model.ReadingOutbound;
import org.elstere.booktrkr.service.ReadingEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class ReadingEntryController {

    private final ReadingEntryService service;

    public ReadingEntryController(ReadingEntryService service){
        this.service = service;
    }

    @GetMapping("/readingEntry/all")
    @CrossOrigin(origins="*")
    @ResponseBody
    public List<ReadingOutbound> fetchReadingEntries(){
        List<ReadingOutbound> entries = this.service.getAllReadingEntries();
        log.info(entries.get(0).toString());
        return entries;
    }

    @GetMapping("/readingEntry/{id}")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ReadingOutbound fetchReadngEntryById(@PathVariable UUID id){
        return this.service.getReadingEntryById(id);
    }

    @GetMapping("/readingEntry")
    @CrossOrigin(origins="*")
    @ResponseBody
    public List<ReadingOutbound> searchByTitle(@RequestParam("title") String title){
        return this.service.searchByTitle(title);
    }

    @PostMapping("/readingEntry")
    @CrossOrigin(origins="*")
    @ResponseBody
    public UUID insertReadingEntry(ReadingEntry reading){
        ReadingEntry entry = this.service.insertEntry(reading);
        if(entry!=null){
            return entry.getId();
        } else{
            return new UUID(0,0);
        }
    }
}
