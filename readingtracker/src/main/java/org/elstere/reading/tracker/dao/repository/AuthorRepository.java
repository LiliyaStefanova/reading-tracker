package org.elstere.reading.tracker.dao.repository;

import org.elstere.reading.tracker.dao.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends PagingAndSortingRepository<Author, UUID> {

    List<Author> findByNameContains(String name);

    List<Author> findAll();
}
