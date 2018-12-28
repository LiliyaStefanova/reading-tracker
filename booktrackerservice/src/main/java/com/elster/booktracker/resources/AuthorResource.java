package com.elster.booktracker.resources;

import com.elster.booktracker.dao.AuthorDao;
import com.elster.booktracker.resources.definitions.Author;
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

@Path("/api/authors")
@Api("Author Data")
public class AuthorResource extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorResource.class);

    private final AuthorDao authorDao;

    @Inject
    public AuthorResource(final AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @GET
    @Path("author/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("id") long id) {
        Optional<Author> author = authorDao.findAuthorById(id);
        return this.optionalResponse(author);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthors() {
        List<String> authors = this.authorDao.getAllAuthorNames();
        return this.successResponse(authors);
    }

    @POST
    @Path("author")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author) {
        long id = authorDao.insert(author.getName(), author.getBio(), author.getNotes());
        return this.successResponse(id);
    }

    @DELETE
    @Path("author/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAuthor(@PathParam("id") long id) {
        this.authorDao.deleteAuthor(id);
        return this.successResponse("OK");
    }
}
