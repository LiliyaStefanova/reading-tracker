package org.elstere.booktrkr.service;

import org.elstere.booktrkr.dao.Genre;
import org.elstere.booktrkr.dao.GenreRepository;
import org.elstere.booktrkr.dao.ReadingEntry;
import org.elstere.booktrkr.dao.ReadingEntryRepository;
import org.elstere.booktrkr.model.GenreInbound;
import org.elstere.booktrkr.model.GenreOutbound;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
        return genres.stream().map(g -> new GenreOutbound(g.getId(), g.getName(), g.getDescription()))
                .collect(Collectors.toSet());
    }

    public GenreOutbound getGenreById(long id){
        Genre genre = this.repository.findById(id);
        return new GenreOutbound(genre.getId(), genre.getName(), genre.getDescription());
    }

    public List<String> getAllReadingEntriesForGenre(long id){
        Genre genre = this.repository.findById(id);
        return genre.getReadingEntries().stream().map(ReadingEntry::getTitle).collect(Collectors.toList());
    }

    public Genre insertGenre(GenreInbound inbound){
        String name = inbound.getName();
        String descr = inbound.getDescription();
        Genre genre = new Genre(name, descr);
        return this.repository.save(genre);
    }
}
