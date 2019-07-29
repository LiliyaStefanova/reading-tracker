package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    Genre findById(long id);

    Genre findByNameContains(String searchTerm);

    List<Genre> findAll();

}
