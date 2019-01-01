package resources;

import com.elster.booktracker.dao.BookDao;
import com.elster.booktracker.resources.BookResource;
import com.elster.booktracker.resources.definitions.Book;
import com.elster.booktracker.resources.definitions.BookEnriched;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {

    private static final BookDao mockDao = mock(BookDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BookResource(mockDao))
            .build();

    private final BookEnriched bookEnriched = new BookEnriched(1, "title", "author", "category", "genre", "notes", "status", false);
    private final Book bookUnenriched = new Book(0, "title", 2, 3, "genre", "notes", "status", true);

    @Test
    public void getBookByIdTest(){
        when(mockDao.findById(1))
                .thenReturn(Optional.of(bookEnriched));

        Response actual = resources.client().target("/api/books/book/1")
                        .request().get();
        Mockito.verify(mockDao).findById(1);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());

    }

    @Test
    public void getAllBooksTest(){

        when(mockDao.getAllBooks())
                .thenReturn(Arrays.asList(bookEnriched, new BookEnriched()));

        Response actual = resources.client().target("/api/books/all")
                .request().get();

        Mockito.verify(mockDao).getAllBooks();
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());

    }

    //TODO test the search method

    @Test
    public void addNewBookTest(){
        when(mockDao.insert(2, 3, "genre", "notes",
                "status", true, "title"))
        .thenReturn(7L);

        Response actual = resources.client().target("/api/books/book")
                .request().post(Entity.json(bookUnenriched));

        Mockito.verify(mockDao).insert(2, 3, "genre", "notes",
                "status", true, "title");
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
    }

    @Test
    public void deleteBookTest(){
        doNothing().when(mockDao).deleteById(5);

        Response actual = resources.client().target("/api/books/book/5")
                .request().delete();

        Mockito.verify(mockDao).deleteById(5);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
    }


}
