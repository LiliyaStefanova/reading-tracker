package org.elstere.booktrkr.service;

import org.elstere.booktrkr.dao.Genre;
import org.elstere.booktrkr.dao.repository.GenreRepository;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.exceptions.BookTrackerBadRequestException;
import org.elstere.booktrkr.api.entities.inbound.GenreInbound;
import org.elstere.booktrkr.api.entities.outbound.GenreOutbound;
import org.elstere.booktrkr.exceptions.BookTrackerServiceException;
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
        return genres.stream().map(this::createGenreOutbound)
                .collect(Collectors.toSet());
    }

    public Optional<GenreOutbound> getGenreById(UUID id){
        Optional<Genre> maybeGenre = this.repository.findById(id);
        if(maybeGenre.isEmpty()){
            return Optional.empty();
        }
        Genre unwrapped = maybeGenre.get();
        return Optional.of(this.createGenreOutbound(unwrapped));
    }

    public List<GenreOutbound> searchGenreByName(String name){

        return this.repository.findByName(name).stream().map(this::createGenreOutbound).collect(Collectors.toList());
    }

    public List<String> getAllReadingEntriesForGenre(UUID id){
        Optional<Genre> maybeGenre = this.repository.findById(id);
        if(maybeGenre.isEmpty()){
            return List.of();
        }
        return maybeGenre.get().getReadingEntries().stream().map(ReadingEntry::getTitle).collect(Collectors.toList());
    }

    public UUID insertGenre(GenreInbound inbound){
            Genre genre = new Genre(inbound.getName(), inbound.getCategory(), inbound.getDescription());
            genre.setCreated_ts(Timestamp.from(Instant.now()));
            this.repository.save(genre);
            if (genre.getId()==null) {
                return null;
            }
            return genre.getId();
    }

    private GenreOutbound createGenreOutbound(Genre genre){
        return GenreOutbound.builder()
                .id(genre.getId())
                .name(genre.getName())
                .category(genre.getCategory())
                .description(genre.getDescription())
                .created_ts(genre.getCreated_ts())
                .build();
    }
}
