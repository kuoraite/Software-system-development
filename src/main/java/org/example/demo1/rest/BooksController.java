package org.example.demo1.rest;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.persistence.BooksDAO;
import org.example.demo1.rest.contracts.BookDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Setter
@Getter
@ApplicationScoped
@Path("/books")
public class BooksController {

    @Inject
    private BooksDAO booksDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Book book = booksDAO.findOne(id);

        if(book == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());

        return Response.ok(bookDto).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(BookDto bookDto) {
        Book bookToCreate = new Book();

        bookToCreate.setTitle(bookDto.getTitle());
        bookToCreate.setPrice(bookDto.getPrice());

        this.booksDAO.persist(bookToCreate);

        return Response.status(Response.Status.CREATED).entity(bookDto).build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final Long bookId, BookDto bookDto) {
        try {
            Book existingBook = booksDAO.findOne(bookId);
            if (bookId == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingBook.setTitle(bookDto.getTitle());
            existingBook.setPrice(bookDto.getPrice());
            booksDAO.update(existingBook);

            return Response.ok(bookDto).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
