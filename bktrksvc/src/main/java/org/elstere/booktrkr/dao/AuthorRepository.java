package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findById(long id);

    Author findByNameContains(String name);

    List<Author> findAll();
}
