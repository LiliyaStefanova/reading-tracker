package resources;

import com.elster.booktracker.dao.CategoryDao;
import com.elster.booktracker.resources.CategoriesResource;
import com.elster.booktracker.resources.definitions.Category;
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
public class CategoryResourceTest {

    private static final CategoryDao mockDao = mock(CategoryDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new CategoriesResource(mockDao))
            .build();

    private final Category category = new Category();

    @Test
    public void getCategoryByIdTest(){
        when(mockDao.findCategoryById(1))
                .thenReturn(Optional.of(category));

        Response actual = resources.client().target("/api/categories/category/1")
                .request().get();
        Mockito.verify(mockDao).findCategoryById(1);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());
    }

    @Test
    public void getAllCategoriesTest(){
        when(mockDao.getAllCategoryNames())
                .thenReturn(Arrays.asList("cat 1", "cat 2"));

        Response actual = resources.client().target("/api/categories/all")
                .request().get();

        Mockito.verify(mockDao).getAllCategoryNames();
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
        Assert.assertNotNull(actual.getEntity());

    }

    @Test
    public void addNewCategoryTest(){
        when(mockDao.insert("name", "description"))
                .thenReturn(2L);

        Response actual = resources.client().target("/api/categories/category")
                .request().post(Entity.json(new Category("name", "description")));

        Mockito.verify(mockDao).insert("name", "description");
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
    }

    @Test
    public void deleteCategoryTest(){
        doNothing().when(mockDao).deleteCategory(3);

        Response actual = resources.client().target("/api/categories/category/3")
                .request().delete();

        Mockito.verify(mockDao).deleteCategory(3);
        Assert.assertThat(actual.getStatus(), is(HttpStatus.SC_OK));
    }
}
