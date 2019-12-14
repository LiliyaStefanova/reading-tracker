package org.elstere.booktrkr.dao.repository;

import org.elstere.booktrkr.dao.ReadingRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadingRecordRepository extends CrudRepository<ReadingRecord, UUID> {


    List<ReadingRecord> findAll();


    Optional<ReadingRecord> findById(UUID readingRecordId);

    //TODO - custom query to find the reading record by book title
}
