package org.example.demo1.persistence;

import lombok.Setter;
import org.example.demo1.entities.Book;
import org.example.demo1.entities.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Setter
@ApplicationScoped
public class PublishersDAO {

    @Inject
    private EntityManager em;

    public List<Publisher> loadAll() {
        return em.createNamedQuery("Publisher.findAll", Publisher.class).getResultList();
    }

    public void persist(Publisher publisher){
        this.em.persist(publisher);
    }

    public Publisher findOne(Long id) {
        return em.find(Publisher.class, id);
    }
}
