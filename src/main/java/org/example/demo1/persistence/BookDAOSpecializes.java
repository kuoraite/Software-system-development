package org.example.demo1.persistence;

import org.example.demo1.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Specializes
public class BookDAOSpecializes extends BooksDAO{

    @Inject
    EntityManager em;

    @Override
    public void updateTitle(Long bookId, String newTitle){
        System.out.println("Specializes BookDAO");
        Book book = em.find(Book.class, bookId);

        if(book != null){
            book.setTitle(newTitle.toUpperCase());
            em.merge(book);
        }
    }
}
