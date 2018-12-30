package com.elster.booktracker.resources;

import com.elster.booktracker.dao.BookDao;
import com.elster.booktracker.exceptions.BookTrackerException;
import com.elster.booktracker.resources.definitions.Book;
import com.elster.booktracker.resources.definitions.BookEnriched;
import com.elster.booktracker.utils.rest.BaseResource;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Api("Books Data")
public class BookResource extends BaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(BookResource.class);

    private BookDao bookDao;

    @Inject
    public BookResource(BookDao bookDao){
        this.bookDao = bookDao;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("book/{id}")
    public Response getBook(@PathParam("id") long id){

        Optional<BookEnriched> book = this.bookDao.findById(id);
        return this.optionalResponse(book);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("book/search")
    public Response findBook(@QueryParam("title") String title, @QueryParam("author") String author){
        //TODO title encoding
        List<Book> books = new ArrayList<>();
        if(title == null){
            books = this.bookDao.searchByTitle("", author);
        } else if(author == null){
            books = this.bookDao.searchByTitle(title, "");
        } else{
            books = this.bookDao.searchByTitle(title, author);
        }
        return this.successResponse(books);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getAllBooks(){

        return this.successResponse(this.bookDao.getAllBooks());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("book")
    public Response createBook(Book book){
        //TODO handle exceptions
        long id = this.bookDao.insert(book.getAuthor(), book.getCategory(),
                book.getGenre(), book.getNotes(), book.getStatus(), book.isFavourite(), book.getTitle());
        return this.successResponse(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("book/{id}")
    public Response updateBook(@PathParam("id") long id, Book book) throws BookTrackerException{
        long finalId = this.bookDao.updateIfExists(id, book.getAuthor(), book.getCategory(), book.getGenre(),
                book.getNotes(), book.getStatus(), book.isFavourite(), book.getTitle());
        return this.successResponse(finalId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("book/{id}")
    public Response deleteBook(@PathParam("id") long id){
        this.bookDao.deleteById(id);
        //TODO this response needs to be better
        return this.successResponse("OK");
    }

}
