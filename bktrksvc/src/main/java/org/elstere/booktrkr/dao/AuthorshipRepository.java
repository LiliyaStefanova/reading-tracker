package org.elstere.booktrkr.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorshipRepository extends CrudRepository<Authorship, UUID> {
}
