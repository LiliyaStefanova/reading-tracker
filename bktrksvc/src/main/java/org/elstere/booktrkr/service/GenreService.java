package org.elstere.booktrkr.service;

import org.elstere.booktrkr.dao.Genre;
import org.elstere.booktrkr.dao.GenreRepository;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.dao.ReadingEntryRepository;
import org.elstere.booktrkr.exceptions.BookTrackerBadRequestException;
import org.elstere.booktrkr.model.GenreInbound;
import org.elstere.booktrkr.model.GenreOutbound;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private GenreRepository repository;

    public GenreService(GenreRepository repository){
        this.repository = repository;
    }

    public Set<GenreOutbound> getAllGenres(){
        Set<Genre> genres = new HashSet<>(this.repository.findAll());
        return genres.stream().map(g -> new GenreOutbound(g.getId(), g.getName(), g.getCategory(), g.getDescription()))
                .collect(Collectors.toSet());
    }

    public Optional<GenreOutbound> getGenreById(long id){
        Genre genre = this.repository.findById(id);
        if(genre == null){
            return Optional.empty();
        }
        return Optional.of(new GenreOutbound(genre.getId(), genre.getName(), genre.getCategory(), genre.getDescription()));
    }

    public List<String> getAllReadingEntriesForGenre(long id){
        Genre genre = this.repository.findById(id);
        return genre.getReadingEntries().stream().map(ReadingEntry::getTitle).collect(Collectors.toList());
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
            this.repository.save(genre);
            if (genre.getId() > 0L) {
                GenreOutbound outbound = new GenreOutbound(genre.getId(), genre.getName(), genre.getCategory(), genre.getDescription());
                return Optional.of(outbound);
            }
        }
            return Optional.empty();
    }
}
