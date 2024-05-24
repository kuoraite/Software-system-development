package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Books {

    @Inject
    private BooksDAO booksDAO;

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

    @Transactional
    public void createBook(){
        this.booksDAO.persist(bookToCreate);
    }
}
