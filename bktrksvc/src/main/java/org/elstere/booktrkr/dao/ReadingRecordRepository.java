package org.elstere.booktrkr.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReadingRecordRepository extends CrudRepository<ReadingRecord, UUID> {


}
