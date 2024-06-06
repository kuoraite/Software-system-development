package org.example.demo1.persistence;

import org.example.demo1.entities.Book;

import javax.transaction.Transactional;
import java.util.List;

public interface IBooksDAO {
    List<Book> loadAll();
    void persist(Book book);
    Book findOne(Long id);
    List<Book> findBooksByPublisherId(Long publisherId);
    void remove(Long bookId);
    void update(Book book);
    void updateTitle(Long bookId, String newTitle) throws InterruptedException;
}
