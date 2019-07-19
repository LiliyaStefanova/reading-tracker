package org.elstere.booktrkr.api;

import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.dao.ReadingEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReadingEntryController {

    private final ReadingEntryRepository repository;

    public ReadingEntryController(ReadingEntryRepository repository){
        this.repository = repository;
    }

    @GetMapping("/readingEntry/all")
    @ResponseBody
    public List<ReadingEntry> fetchReadingEntries(){
        return this.repository.findAll();
    }

    @GetMapping("/readingEntry/{id}")
    @ResponseBody
    public ReadingEntry fetchReadngEntryById(@PathVariable long id){
        return this.repository.findById(id);
    }

    @GetMapping("/readingEntry")
    @ResponseBody
    public ReadingEntry searchByTitle(@RequestParam("title") String title){
        return this.repository.findByTitleContains(title);
    }

    @PostMapping("/readingEntry")
    @ResponseBody
    public long insertReadingEntry(ReadingEntry reading){
        ReadingEntry entry = this.repository.save(reading);
        if(entry!=null){
            return entry.getId();
        } else{
            return 0L;
        }
    }
}
