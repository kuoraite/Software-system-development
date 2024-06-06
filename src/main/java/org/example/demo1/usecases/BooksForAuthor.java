package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Author;
import org.example.demo1.entities.Book;
import org.example.demo1.interceptors.LoggedInvocation;
import org.example.demo1.persistence.AuthorsDAO;
import org.example.demo1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class BooksForAuthor implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private Books books;

    @Getter
    private List<Book> assignedBooks;

    @Getter @Setter
    private Book bookToAssign;

    @Getter @Setter
    private Author authorToAssignTo;

    @Getter @Setter
    private long bookId;

    @Getter @Setter
    private String errorMessage;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        long authorId = Long.parseLong(requestParameters.get("authorId"));
        authorToAssignTo = authorsDAO.findOne(authorId);
        assignedBooks = booksDAO.findBooksByPublisherId(authorId);
    }

    public List<Book> getAssignedBooks(){
        return authorToAssignTo.getBooks();
    }

    @Transactional
    public void assignBook() {
        bookToAssign = booksDAO.findOne(bookId);
        bookToAssign.getAuthors().add(authorToAssignTo);
        authorToAssignTo.getBooks().add(bookToAssign);

        booksDAO.update(bookToAssign);
        authorsDAO.update(authorToAssignTo);
    }

    public List<Book> getAvailableBooks() {
        List<Book> allBooks = books.getAllBooks();
        List<Book> assignedBooks = authorToAssignTo.getBooks();

        List<Book> availableBooks = new ArrayList<>(allBooks);
        if(assignedBooks != null) {
            availableBooks.removeAll(assignedBooks);
        }

        return availableBooks;
    }

    @Transactional
    @LoggedInvocation
    public void updateAuthorName(){
        try{
            authorsDAO.update(authorToAssignTo);
        } catch (OptimisticLockException ole){
            errorMessage = "Optimistic lock exception: " + ole;
        }
    }
}
