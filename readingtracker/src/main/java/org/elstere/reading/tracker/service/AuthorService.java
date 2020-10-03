package org.elstere.reading.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.elstere.reading.tracker.api.entities.outbound.AuthorOutbound;
import org.elstere.booktrkr.dao.*;
import org.elstere.reading.tracker.dao.Author;
import org.elstere.reading.tracker.dao.Authorship;
import org.elstere.reading.tracker.dao.ReadingEntry;
import org.elstere.reading.tracker.dao.repository.AuthorRepository;
import org.elstere.reading.tracker.dao.repository.AuthorshipRepository;
import org.elstere.reading.tracker.dao.repository.ReadingEntryRepository;
import org.elstere.reading.tracker.exceptions.BookTrackerNotFoundException;
import org.elstere.reading.tracker.exceptions.BookTrackerServiceException;
import org.elstere.reading.tracker.api.entities.inbound.AuthorInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorService {

    private AuthorRepository authorRepository;

    private ReadingEntryRepository readingEntryRepository;

    private AuthorshipRepository authorshipRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository, ReadingEntryRepository readingEntryRepository, AuthorshipRepository authorshipRepository) {
        this.authorRepository = authorRepository;
        this.readingEntryRepository = readingEntryRepository;
        this.authorshipRepository = authorshipRepository;
    }

    public Optional<AuthorOutbound> getAuthorById(UUID id) {

        Optional<Author> maybeAuthor =  authorRepository.findById(id);
        if(maybeAuthor.isPresent()){
            Author author = maybeAuthor.get();
            return Optional.of(this.createAuthorOutboundPayload(author));
        } else{
            return Optional.empty();
        }
    }

    public List<AuthorOutbound> searchByName(String searchTerm) {
        return authorRepository.findByNameContains(searchTerm)
                .stream()
                .map(this::createAuthorOutboundPayload)
                .collect(Collectors.toList());
    }

    public Set<AuthorOutbound> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::createAuthorOutboundPayload)
                .collect(Collectors.toSet());
    }



    public UUID insertAuthor(AuthorInbound authorInbound) {

        Author newAuthor = Author.builder().name(authorInbound.getName())
                .bio(authorInbound.getBio())
                .notes(authorInbound.getNotes())
                .website(authorInbound.getWebsite())
                .authorship(Set.of())
                .created_ts(Timestamp.from(Instant.now())).build();
        this.authorRepository.save(newAuthor);

        if (newAuthor.getId() != null) {
            return newAuthor.getId();
        } else {
            log.error("New entry with name {} could not be saved to the DB", authorInbound.getName());
            throw new BookTrackerServiceException("Could not save new author entry");
        }
    }

    public UUID associatedReadingEntryWithAuthor(UUID readingEntryID, UUID authorId) {

        Authorship newAuthorship = new Authorship();

        Optional<Author> maybeAuthor = this.authorRepository.findById(authorId);
        Optional<ReadingEntry> maybeReadingEntry = this.readingEntryRepository.findById(readingEntryID);

        if (maybeAuthor.isEmpty() || maybeReadingEntry.isEmpty()) {
            throw new BookTrackerNotFoundException("Could not find author or reading entry you are trying to associate with them");
        }
        newAuthorship.setAuthor(maybeAuthor.get());
        newAuthorship.setReadingEntry(maybeReadingEntry.get());
        newAuthorship.setCreated_ts(Timestamp.from(Instant.now()));
        authorshipRepository.save(newAuthorship);

        if (newAuthorship.getId() != null) {
            log.error("New authorship entry for author {} and reading entry {} could not be created in the DB", authorId, readingEntryID);
            throw new BookTrackerServiceException("Could not associate reading entry and author");

        } else {
            //TODO - do we need to do this? Test
            maybeAuthor.get().getAuthorship().add(newAuthorship);
        }

        return newAuthorship.getId();
    }

    private AuthorOutbound createAuthorOutboundPayload(Author author){
        return AuthorOutbound.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .notes(author.getNotes())
                .website(author.getNotes())
                .build();
    }
}
