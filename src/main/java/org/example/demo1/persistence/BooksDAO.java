package org.example.demo1.persistence;

import org.example.demo1.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BooksDAO {

    @Inject
    private EntityManager em;

    public List<Book> loadAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Long id){
        return em.find(Book.class, id);
    }

    @Transactional
    public List<Book> findBooksByPublisherId(Long publisherId) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.publisher.id = :publisherId", Book.class);
        query.setParameter("publisherId", publisherId);
        return query.getResultList();
    }

    @Transactional
    public void remove(Long bookId){
        Book book = em.find(Book.class, bookId);
        book.setPublisher(null);
        em.merge(book);
    }

    public void update(Book book){
        em.merge(book);
    }
}
