package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends PagingAndSortingRepository<Genre, UUID> {

    Genre findByNameContains(String searchTerm);

    List<Genre> findAll();

}
