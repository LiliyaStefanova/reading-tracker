package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends PagingAndSortingRepository<Author, UUID> {

    Author findByNameContains(String name);

    List<Author> findAll();
}
