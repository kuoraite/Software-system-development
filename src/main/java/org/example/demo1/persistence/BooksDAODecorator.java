package org.example.demo1.persistence;

import org.example.demo1.entities.Book;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Decorator
public class BooksDAODecorator implements IBooksDAO{

    @Inject
    @Delegate
    private IBooksDAO booksDAO;

    public List<Book> loadAll() {
        System.out.println("Using BooksDAO Decorator");
        return booksDAO.loadAll();
    }

    public void persist(Book book){
        System.out.println("Using BooksDAO Decorator");
        booksDAO.persist(book);
    }

    public Book findOne(Long id){
        System.out.println("Using BooksDAO Decorator");
        return booksDAO.findOne(id);
    }

    @Transactional
    public List<Book> findBooksByPublisherId(Long publisherId) {
        System.out.println("Using BooksDAO Decorator");
        return booksDAO.findBooksByPublisherId(publisherId);
    }

    @Transactional
    public void remove(Long bookId){
        System.out.println("Using BooksDAO Decorator");
        booksDAO.remove(bookId);
    }

    public void update(Book book){
        System.out.println("Using BooksDAO Decorator");
        booksDAO.update(book);
    }

    @Transactional
    public void updateTitle(Long bookId, String newTitle) throws InterruptedException {
        System.out.println("Using BooksDAO Decorator");
        booksDAO.updateTitle(bookId, newTitle);
    }
}
