package org.example.demo1.usecases;

import lombok.Getter;
import lombok.Setter;
import org.example.demo1.entities.Author;
import org.example.demo1.entities.Book;
import org.example.demo1.persistence.AuthorsDAO;
import org.example.demo1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {

    @Inject
    private AuthorsDAO authorsDAO;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> allAuthors;

    @PostConstruct
    public void init() {
        loadAllAuthors();
    }

    private void loadAllAuthors(){
        this.allAuthors = authorsDAO.loadAll();
    }

    @Transactional
    public void createAuthor(){
        this.authorsDAO.persist(authorToCreate);
    }
}
