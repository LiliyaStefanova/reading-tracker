package org.elstere.booktrkr.service;

import org.elstere.booktrkr.dao.Genre;
import org.elstere.booktrkr.dao.repository.GenreRepository;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.exceptions.BookTrackerBadRequestException;
import org.elstere.booktrkr.api.entities.inbound.GenreInbound;
import org.elstere.booktrkr.api.entities.outbound.GenreOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository){
        this.repository = repository;
    }

    public Set<GenreOutbound> getAllGenres(){
        Set<Genre> genres = new HashSet<>(this.repository.findAll());
        return genres.stream().map(g -> new GenreOutbound(g.getId(), g.getName(), g.getCategory(), g.getDescription(), g.getCreated_ts()))
                .collect(Collectors.toSet());
    }

    public Optional<GenreOutbound> getGenreById(UUID id){
        Optional<Genre> genre = this.repository.findById(id);
        if(genre.isEmpty()){
            return Optional.empty();
        }
        Genre unwrapped = genre.get();
        return Optional.of(new GenreOutbound(unwrapped.getId(), unwrapped.getName(), unwrapped.getCategory(),
                unwrapped.getDescription(), unwrapped.getCreated_ts()));
    }

    public Optional<GenreOutbound> searchGenreByName(String name){
        Optional<Genre> maybeGenre = this.repository.findByName(name);
        if(maybeGenre.isPresent()){

        }
    }

    public List<String> getAllReadingEntriesForGenre(UUID id){
        Optional<Genre> genre = this.repository.findById(id);
        //FIXME
        return genre.get().getReadingEntries().stream().map(ReadingEntry::getTitle).collect(Collectors.toList());
    }

    public Optional<GenreOutbound> insertGenre(GenreInbound inbound){
        String name = inbound.getName();
        String category = inbound.getCategory();
        String descr = inbound.getDescription();
        //TODO turn into an enum
        if(!List.of("fiction", "non-fiction").contains(category)){
            throw new BookTrackerBadRequestException();
        } else {
            Genre genre = new Genre(name, category, descr);
            genre.setCreated_ts(Timestamp.from(Instant.now()));
            this.repository.save(genre);
            if (genre.getId()!=null) {
                GenreOutbound outbound = new GenreOutbound(genre.getId(), genre.getName(), genre.getCategory(), genre.getDescription(), genre.getCreated_ts());
                return Optional.of(outbound);
            }
        }
            return Optional.empty();
    }
}
