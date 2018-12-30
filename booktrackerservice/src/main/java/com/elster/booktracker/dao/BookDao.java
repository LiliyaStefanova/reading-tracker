package com.elster.booktracker.dao;

import com.elster.booktracker.dao.mappers.BookEnrichedMapper;
import com.elster.booktracker.dao.mappers.BookMapper;
import com.elster.booktracker.resources.definitions.Book;
import com.elster.booktracker.resources.definitions.BookEnriched;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
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
                "values(nextval('books_id_seq'), ?, ?,"+
                "?, ?, ?, ?, ?)")
    @GetGeneratedKeys("id")
    long insert(long author_id, long category_id, String genre,
                                String notes, String status, boolean favourite, String title);

    @SqlUpdate("UPDATE ")
    long update(Book book, long id);

    @SqlUpdate("DELETE FROM books WHERE id = :id")
    void deleteById(@Bind("id") long id);

    @SqlQuery("SELECT b.id, b.title, a.id as author, c.id as category, b.genre, b.note, b.status, b.favourite " +
                "FROM books b " +
                "LEFT JOIN author a "+
                "ON b.author_id = a.id "+
                "LEFT JOIN category c "+
                "ON b.category_id = c.id "+
                "WHERE b.id = :id")
    @RegisterRowMapper(BookMapper.class)
    Optional<Book> findById(@Bind("id") long id);

    @SqlQuery("SELECT b.id, b.title, a.id as author, c.id as category, b.genre, b.note, b.status, b.favourite" +
              " FROM books b" +
              " LEFT JOIN author a" +
              " ON b.author_id = a.id"+
              " LEFT JOIN category c" +
              " ON b.category_id = c.id"+
              " where b.title LIKE :title")
    @RegisterRowMapper(BookMapper.class)
    List<Book> searchByTitle(@Bind("title") String title);

    @SqlQuery("SELECT b.id, b.title, a.id as author, c.id as category, b.genre, b.note, b.status, b.favourite" +
            " FROM books b" +
            " LEFT JOIN author a" +
            " ON b.author_id = a.id"+
            " LEFT JOIN category c" +
            " ON b.category_id = c.id"+
            " where a.name LIKE :author")
    @RegisterRowMapper(BookMapper.class)
    List<Book> searchByAuthor(@Bind("author") String author);

    @SqlQuery("SELECT b.id, b.title, a.name as author, c.name as category, b.genre, b.note,b.status, b.favourite " +
            " FROM books b" +
            " LEFT JOIN author a" +
            " ON a.id = b.author_id" +
            " LEFT JOIN category c" +
            " ON c.id = b.category_id")
    @RegisterRowMapper(BookEnrichedMapper.class)
    List<BookEnriched> getAllBooks();

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
