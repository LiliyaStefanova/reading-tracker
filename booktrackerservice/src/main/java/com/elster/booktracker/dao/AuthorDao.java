package com.elster.booktracker.dao;

import com.elster.booktracker.resources.definitions.Author;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository;
import ru.vyarus.guicey.jdbi3.tx.InTransaction;

import java.util.List;
import java.util.Optional;

@JdbiRepository
@InTransaction
public interface AuthorDao {

    @SqlUpdate("INSERT INTO author (id, name, bio, notes) values(nextval('author_id_seq'), ?, ?, ?)")
    @GetGeneratedKeys("id")
    long insert(String name, String bio, String notes);

    @SqlQuery("SELECT * FROM author WHERE id = :id")
    Optional<Author> findAuthorById(@Bind("id") long id);

    @SqlQuery("SELECT name FROM author")
    List<String> getAllAuthorNames();

    @SqlQuery("DELETE FROM author WHERE id = :id")
    void deleteAuthor(@Bind("id") long id);

}
