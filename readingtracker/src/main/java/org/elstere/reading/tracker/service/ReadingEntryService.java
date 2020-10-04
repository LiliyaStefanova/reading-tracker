package org.elstere.reading.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.api.entities.inbound.ReadingEntryInbound;
import org.elstere.reading.tracker.dao.Author;
import org.elstere.reading.tracker.dao.Authorship;
import org.elstere.reading.tracker.dao.Genre;
import org.elstere.reading.tracker.dao.ReadingEntry;
import org.elstere.reading.tracker.dao.repository.AuthorRepository;
import org.elstere.reading.tracker.dao.repository.GenreRepository;
import org.elstere.reading.tracker.dao.repository.ReadingEntryRepository;
import org.elstere.reading.tracker.api.entities.outbound.ReadingEntryOutbound;
import org.elstere.reading.tracker.api.entities.common.ReadingType;
import org.elstere.reading.tracker.enums.Medium;
import org.elstere.reading.tracker.exceptions.BookTrackerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReadingEntryService {

    private final ReadingEntryRepository repository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public ReadingEntryService(ReadingEntryRepository repository, GenreRepository genreRepository,
                               AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    public List<ReadingEntryOutbound> getAllReadingEntries() {
        List<ReadingEntry> entries = this.repository.findAll();
        return entries.stream().map(this::createOutbound).collect(Collectors.toList());
    }

    public Optional<ReadingEntryOutbound> getReadingEntryById(UUID id) {
        Optional<ReadingEntry> maybeEntry = this.repository.findById(id);
        return maybeEntry.map(this::createOutbound);
    }

    public List<ReadingEntryOutbound> searchByTitle(String title) {
        List<ReadingEntry> entries = this.repository.findAll();
        return entries.stream().map(this::createOutbound).collect(Collectors.toList());
    }

    //TODO create ReadingInbound
    public ReadingEntryOutbound insertEntry(ReadingEntryInbound entry) {

        // TODO - authorships
        // TODO - reading record
        ReadingEntry newEntry = createReadingEntry(entry);
        ReadingEntry createEntry = this.repository.save(newEntry);

        return createOutbound(createEntry);
    }

    private ReadingEntryOutbound createOutbound(ReadingEntry entry) {
        return ReadingEntryOutbound.builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .type(ReadingType.valueOf(entry.getType()))
                .medium(Medium.valueOf(entry.getMedium()))
                .language(entry.getLanguage())
                .authors(entry.getAuthorships().stream().map(a -> a.getAuthor().getName()).collect(Collectors.joining(";")))
                .genre(entry.getGenre().getName())
                .build();
    }

    private ReadingEntry createReadingEntry(ReadingEntryInbound inboundPayload){

        Optional<Genre> genre = this.genreRepository.findByName(inboundPayload.getGenre());
        if(genre.isEmpty()){
            throw new BookTrackerServiceException("Could not find genre");
        }

        return ReadingEntry.builder()
                .title(inboundPayload.getTitle())
                .type(inboundPayload.getType().name())
                .genre(genre.get())
                .medium(inboundPayload.getMedium().name())
                .favourite(false)
                .language(inboundPayload.getLanguage())
                .publisher(inboundPayload.getPublisher())
                .edition(inboundPayload.getEdition())
                .created_ts(Timestamp.from(Instant.now()))
                .build();
    }


}
