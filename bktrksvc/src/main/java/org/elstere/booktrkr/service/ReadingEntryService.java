package org.elstere.booktrkr.service;

import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.dao.ReadingEntryRepository;
import org.elstere.booktrkr.model.ReadingOutbound;
import org.elstere.booktrkr.model.ReadingType;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReadingEntryService {

    private final ReadingEntryRepository repository;

    public ReadingEntryService(ReadingEntryRepository repository){
        this.repository = repository;
    }


    public List<ReadingOutbound> getAllReadingEntries(){
        List<ReadingEntry> entries = this.repository.findAll();
        return entries.stream().map(this::createOutbound).collect(Collectors.toList());
    }

    public ReadingOutbound getReadingEntryById(UUID id){
        Optional<ReadingEntry> entry = this.repository.findById(id);
        return this.createOutbound(entry.get());
    }

    public List<ReadingOutbound> searchByTitle(String title){
        List<ReadingEntry> entries = this.repository.findAll();
        return entries.stream().map(this::createOutbound).collect(Collectors.toList());
    }

    //TODO create ReadingInbound
    public ReadingEntry insertEntry(ReadingEntry entry){
        entry.setCreated_ts(Timestamp.from(Instant.now()));
        ReadingEntry newEntry = this.repository.save(entry);
        return newEntry;
    }

    private ReadingOutbound createOutbound(ReadingEntry entry){
        UUID id = entry.getId();
        String title = entry.getTitle();
        ReadingType type = ReadingType.valueOf(entry.getType());
        ReadingOutbound.Medium medium = ReadingOutbound.Medium.valueOf(entry.getMedium());
        String language = entry.getLanguage();
        String authors = entry.getAuthorships().stream().map(a -> a.getAuthor().getName()).collect(Collectors.joining(";"));
        String genre = entry.getGenre().getName();

        return new ReadingOutbound(id, title, authors, genre, type, medium, language, "", "");
    }

}
