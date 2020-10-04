package org.elstere.reading.tracker.dao.repository;

import org.elstere.reading.tracker.dao.Authorship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorshipRepository extends CrudRepository<Authorship, UUID> {
}
