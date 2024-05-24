package org.example.demo1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select b from Book as b")
})
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

    @ManyToMany
    private List<Author> authors;

}
