package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Author;
import org.example.demo1.entities.Book;
import org.example.demo1.persistence.AuthorsDAO;
import org.example.demo1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class AuthorsForBook {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private Authors authors;

    @Inject
    private Book book;

    @Getter @Setter
    private Long authorId;

    @Getter @Setter
    private Book bookToAssignTo;

    @Getter @Setter
    private Author authorToAssign;

    @Getter
    private List<Author> booksAuthors;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        long bookId = Long.parseLong(requestParameters.get("bookId"));
        bookToAssignTo = booksDAO.findOne(bookId);
        booksAuthors = authorsDAO.findAuthorsByBookId(bookId);
    }

    @Transactional
    public void assignAuthor() {
        authorToAssign = authorsDAO.findOne(authorId);
        authorToAssign.getBooks().add(bookToAssignTo);
        bookToAssignTo.getAuthors().add(authorToAssign);

        authorsDAO.update(authorToAssign);
        booksDAO.update(bookToAssignTo);
    }

    public List<Author> getAvailableAuthors() {
        List<Author> allAuthors = authors.getAllAuthors();
        List<Author> assignedAuthors = bookToAssignTo.getAuthors();

        List<Author> availableAuthors = new ArrayList<>(allAuthors);
        if(assignedAuthors != null) {
            availableAuthors.removeAll(assignedAuthors);
        }

        return availableAuthors;
    }
}
