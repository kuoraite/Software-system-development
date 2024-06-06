package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.interceptors.LoggedInvocation;
import org.example.demo1.persistence.IBooksDAO;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class Books implements Serializable {

    @Getter @Setter
    private String errorMessage;
    @Inject
    private IBooksDAO booksDAO;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @Getter
    private List<Book> allBooks;

    @PostConstruct
    public void init() {
        loadAllBooks();
    }

    private void loadAllBooks(){
        this.allBooks = booksDAO.loadAll();
    }

    @LoggedInvocation
    @Transactional
    public void createBook(){
        this.booksDAO.persist(bookToCreate);
    }
}
