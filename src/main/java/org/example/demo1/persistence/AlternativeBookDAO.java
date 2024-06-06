package org.example.demo1.persistence;

import org.example.demo1.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Alternative
@ApplicationScoped
public class AlternativeBookDAO implements IBooksDAO {
    @Inject
    private EntityManager em;

    @Override
    public List<Book> loadAll() {
        System.out.println("Using alternative BooksDAO");
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    @Override
    public void persist(Book book){
        System.out.println("Using alternative BooksDAO");
        this.em.persist(book);
    }

    @Override
    public Book findOne(Long id){
        System.out.println("Using alternative BooksDAO");
        return em.find(Book.class, id);
    }

    @Override
    @Transactional
    public List<Book> findBooksByPublisherId(Long publisherId) {
        System.out.println("Using alternative BooksDAO");
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.publisher.id = :publisherId", Book.class);
        query.setParameter("publisherId", publisherId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void remove(Long bookId){
        System.out.println("Using alternative BooksDAO");
        Book book = em.find(Book.class, bookId);
        book.setPublisher(null);
        em.merge(book);
    }

    public void update(Book book){
        System.out.println("Using alternative BooksDAO");
        em.merge(book);
    }

    @Override
    @Transactional
    public void updateTitle(Long bookId, String newTitle){
        System.out.println("Using alternative BooksDAO");
        Book book = em.find(Book.class, bookId);

        if(book != null){
            book.setTitle(newTitle);
            em.merge(book);
        }
    }
}
