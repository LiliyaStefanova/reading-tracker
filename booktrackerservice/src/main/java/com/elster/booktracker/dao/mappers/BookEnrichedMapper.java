package com.elster.booktracker.dao.mappers;

import com.elster.booktracker.resources.definitions.BookEnriched;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookEnrichedMapper implements RowMapper<BookEnriched> {
    @Override
    public BookEnriched map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new BookEnriched(rs.getInt("id"), rs.getString("title"),
                rs.getString("author"), rs.getString("category"),
                rs.getString("genre"), rs.getString("note"),
                rs.getString("status"), rs.getBoolean("favourite"));
    }
}
