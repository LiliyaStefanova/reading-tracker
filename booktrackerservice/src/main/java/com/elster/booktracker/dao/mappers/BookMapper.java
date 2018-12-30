package com.elster.booktracker.dao.mappers;

import com.elster.booktracker.resources.definitions.Book;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet rs, StatementContext ctx) throws SQLException {

       return new Book(rs.getInt("id"), rs.getString("title"),
               rs.getLong("author"), rs.getLong("category"),
               rs.getString("genre"), rs.getString("note"),
               rs.getString("status"), rs.getBoolean("favourite"));
    }

}
