package org.elstere.booktrkr.service;

import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.*;
import org.elstere.booktrkr.dao.repository.AuthorRepository;
import org.elstere.booktrkr.dao.repository.AuthorshipRepository;
import org.elstere.booktrkr.dao.repository.ReadingEntryRepository;
import org.elstere.booktrkr.exceptions.BookTrackerNotFoundException;
import org.elstere.booktrkr.exceptions.BookTrackerServiceException;
import org.elstere.booktrkr.api.entities.inbound.AuthorInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
@Slf4j
public class AuthorService {

    private AuthorRepository authorRepository;

    private ReadingEntryRepository readingEntryRepository;

    private AuthorshipRepository authorshipRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository, ReadingEntryRepository readingEntryRepository, AuthorshipRepository authorshipRepository){
        this.authorRepository = authorRepository;
        this.readingEntryRepository = readingEntryRepository;
        this.authorshipRepository = authorshipRepository;
    }

    public Optional<Author> getAuthorById(UUID id){

        Optional<Author> maybeAuthor =  authorRepository.findById(id);
        return maybeAuthor;
    }

    public List<Author> searchByName(String searchTerm){
        return authorRepository.findByNameContains(searchTerm);
    }

    public Set<Author> getAllAuthors(){
        return new HashSet<>(authorRepository.findAll());
    }

    public UUID insertAuthor(AuthorInbound authorInbound){

        Author newAuthor = new Author();
        newAuthor.setName(authorInbound.getName());
        newAuthor.setBio(authorInbound.getBio());
        newAuthor.setNotes(authorInbound.getNotes());
        newAuthor.setWebsite(authorInbound.getNotes());
        newAuthor.setAuthorship(Set.of());
        newAuthor.setCreated_ts(Timestamp.from(Instant.now()));
        this.authorRepository.save(newAuthor);

        if(newAuthor.getId()!=null){
            return newAuthor.getId();
        } else{
            log.error("New entry with name {} could not be saved to the DB", authorInbound.getName());
            throw new BookTrackerServiceException("Could not save new author entry");
        }
    }

    public UUID associatedReadingEntryWithAuthor(UUID readingEntryID, UUID authorId){

        Authorship newAuthorship  = new Authorship();

        Optional<Author> maybeAuthor = this.authorRepository.findById(authorId);
        Optional<ReadingEntry> maybeReadingEntry = this.readingEntryRepository.findById(readingEntryID);

        if(maybeAuthor.isEmpty() || maybeReadingEntry.isEmpty()){
            throw new BookTrackerNotFoundException("Could not find author or reading entry you are trying to associate with them");
        }
        newAuthorship.setAuthor(maybeAuthor.get());
        newAuthorship.setReadingEntry(maybeReadingEntry.get());
        newAuthorship.setCreated_ts(Timestamp.from(Instant.now()));
        authorshipRepository.save(newAuthorship);

        if(newAuthorship.getId()!=null){
            log.error("New authorship entry for author {} and reading entry {} could not be created in the DB", authorId, readingEntryID);
            throw new BookTrackerServiceException("Could not associate reading entry and author");

        } else{
            //TODO - do we need to do this? Test
            maybeAuthor.get().getAuthorship().add(newAuthorship);
        }

        return newAuthorship.getId();
    }
}
