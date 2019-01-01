package resources;

import com.elster.booktracker.dao.AuthorDao;
import com.elster.booktracker.resources.AuthorResource;
import com.elster.booktracker.resources.definitions.Author;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorResourceTest {

    private static final AuthorDao mockDao = mock(AuthorDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AuthorResource(mockDao))
            .build();

    private final Author author = new Author(2, "name", "bio", "notes");

    //TODO test exception handling
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {

    }

    @Test
    public void getAuthorByIdReturnsResultTest() {

        when(mockDao.findAuthorById(2))
                .thenReturn(Optional.of(author));
        Response actual = resources.client().target("/api/authors/author/2")
                .request().get();

        Mockito.verify(mockDao).findAuthorById(2);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());
    }

    @Test
    public void getAuthorByIdNotFoundTest() {
        when(mockDao.findAuthorById(6))
                .thenReturn(Optional.empty());

        Response actual = resources.client().target("/api/authors/author/6")
                .request().get();

        Mockito.verify(mockDao).findAuthorById(6);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_NOT_FOUND));
        Assert.assertNotNull(actual.getEntity());
    }

    @Test
    public void getAllAuthorsTest() {
        List<String> authorList = new ArrayList<>();
        when(mockDao.getAllAuthorNames()).thenReturn(authorList);

        Response actual = resources.client().target("/api/authors/all")
                .request().get();

        Mockito.verify(mockDao).getAllAuthorNames();
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());

    }

    @Test
    public void createAuthorOkTest() {
        Author newAuthor = new Author("new name", "new bio", "new notes");

       when(mockDao.insert("new name", "new bio", "new notes"))
                .thenReturn(7L);

        Response actual = resources.client().target("/api/authors/author")
                .request().post(Entity.json(newAuthor));

        Mockito.verify(mockDao).insert("new name", "new bio", "new notes");
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));

    }

    @Test
    public void deleteAuthorOkTest() {
        doNothing().when(mockDao).deleteAuthor(8L);

        Response actual = resources.client().target("/api/authors/author/8")
                .request().delete();

        Mockito.verify(mockDao).deleteAuthor(8);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
    }

    @After
    public void tearDown() {
        reset(mockDao);
    }
}
