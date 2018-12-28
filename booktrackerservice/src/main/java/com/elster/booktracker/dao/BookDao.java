package com.elster.booktracker.dao;

import com.elster.booktracker.resources.definitions.Book;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository;
import ru.vyarus.guicey.jdbi3.tx.InTransaction;

import java.util.List;
import java.util.Optional;

@InTransaction
@JdbiRepository
public interface BookDao {

    @SqlUpdate("INSERT INTO books (id, author_id, category_id, genre, note, status, favourite, title) " +
                "values(nextval('books_id_seq'), ?, ?, ?, ?, ?, ?, ?)")
    @GetGeneratedKeys("id")
    long insert(Book book);

    @SqlUpdate("DELETE FROM books WHERE id = :id")
    void deleteById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM books WHERE id = :id")
    Optional<Book> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM books where title LIKE :title")
    List<Book> searchByTitle(@Bind("title") String title);

    @SqlQuery("SELECT * FROM books b " +
                "LEFT JOIN author a" +
                "ON b.author_id = a.id" +
                "WHERE a.name LIKE :author")
    List<Book> searchByAuthor(@Bind("author") String author);

    @SqlQuery("SELECT title FROM books")
    List<String> getAllBookTitles();

    @SqlQuery("SELECT DISTINCT a.name FROM books b" +
                "LEFT JOIN author a" +
                "ON b.author_id = a.id")
    List<String> getAllBookAuthors();

    @SqlQuery("SELECT DISTINCT c.name FROM books b" +
                "LEFT JOIN category c" +
                "ON b.category_id = c.id")
    List<String> getAllBookCategories();


}
