package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReadingEntryRepository extends PagingAndSortingRepository<ReadingEntry, Long> {

    ReadingEntry findById(long id);

    ReadingEntry findByTitleContains(String searchTerm);

    List<ReadingEntry> findAll();
}
