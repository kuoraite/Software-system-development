package org.example.demo1.persistence;

import lombok.Setter;
import org.example.demo1.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Setter
@ApplicationScoped
public class AuthorsDAO {

    @Inject
    private EntityManager em;

    public List<Author> loadAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void persist(Author author){
        this.em.persist(author);
    }

    public Author findOne(Long id){
        return em.find(Author.class, id);
    }

    @Transactional
    public List<Author> findAuthorsByBookId(Long bookId) {
        TypedQuery<Author> query = em.createQuery(
                "SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId", Author.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    public void update(Author author){
        em.merge(author);
    }
}
