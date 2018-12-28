package com.elster.booktracker.resources;

import com.elster.booktracker.dao.CategoryDao;
import com.elster.booktracker.resources.definitions.Category;
import com.elster.booktracker.utils.rest.BaseResource;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/categories")
@Api("Custom Categories Data")
public class CategoriesResource extends BaseResource {

    //TODO handle exceptions - use Exception Mapper
    private static final Logger LOG = LoggerFactory.getLogger(CategoriesResource.class);

    private CategoryDao categoryDao;

    @Inject
    public CategoriesResource(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("category/{id}")
    public Response getCategory(@PathParam("id") long id){
        Optional<Category> category = categoryDao.findCategoryById(id);
        return this.optionalResponse(category);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getCategories()
    {
        List<String> categories = this.categoryDao.getAllCategoryNames();
        return this.successResponse(categories);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("category")
    public Response createCategory(Category category){
        long id = this.categoryDao.insert(category.getName(), category.getDescription());
        return this.successResponse(id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("category/{id}")
    public Response deleteCategory(@PathParam("id") long id){
        this.categoryDao.deleteCategory(id);
        return this.successResponse("OK");
    }

}
