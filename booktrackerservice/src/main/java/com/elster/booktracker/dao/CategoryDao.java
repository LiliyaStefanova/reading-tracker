package com.elster.booktracker.dao;

import com.elster.booktracker.resources.definitions.Category;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
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
public interface CategoryDao {

    @SqlUpdate("INSERT INTO category (id, name, description) values(nextval('category_id_seq'), ?, ?)")
    @GetGeneratedKeys("id")
    long insert(String name, String description);

    @SqlQuery("SELECT * FROM category WHERE id = :id")
    @RegisterBeanMapper(Category.class)
    Optional<Category> findCategoryById(@Bind("id") long id);

    @SqlQuery("SELECT name FROM category")
    List<String> getAllCategoryNames();

    @SqlUpdate("DELETE FROM category WHERE id = :id")
    void deleteCategory(@Bind("id") long id);

    //TODO number of books per category

}


