package org.elstere.booktrkr.dao.repository;

import org.elstere.booktrkr.dao.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends PagingAndSortingRepository<Genre, UUID> {

    Optional<Genre> findByName(String name);

    List<Genre> findAll();

}
