package org.elstere.reading.tracker.dao.repository;

import org.elstere.reading.tracker.dao.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends PagingAndSortingRepository<Genre, UUID> {

    Optional<Genre> findByName(String name);

    List<Genre> findAll();

}
