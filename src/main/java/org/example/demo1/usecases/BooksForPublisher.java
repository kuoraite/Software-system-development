package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.entities.Publisher;
import org.example.demo1.persistence.BooksDAO;
import org.example.demo1.persistence.PublishersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class BooksForPublisher {

    @Inject
    private PublishersDAO publishersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private Books books;

    @Getter
    private List<Book> assignedBooks;

    @Getter @Setter
    private Publisher publisher;

    @Getter @Setter
    private Book bookToAssign;

    @Getter @Setter
    private long bookId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        long publisherId = Long.parseLong(requestParameters.get("publisherId"));
        publisher = publishersDAO.findOne(publisherId);
        assignedBooks = booksDAO.findBooksByPublisherId(publisherId);
    }

    public List<Book> getAssignedBooks(){
        return publisher.getBooks();
    }

    @Transactional
    public void assignBook() {
        bookToAssign = booksDAO.findOne(bookId);
        bookToAssign.setPublisher(publisher);
        booksDAO.update(bookToAssign);
    }

    @Transactional
    public void removeBook(Long bookId){
        booksDAO.remove(bookId);
    }

    public List<Book> getAvailableBooks() {
        return books.getAllBooks().stream()
                .filter(book -> book.getPublisher() == null).collect(Collectors.toList());
    }
}
