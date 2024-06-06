package org.example.demo1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select a from Author as a")
})
@Table(name = "AUTHOR")
public class Author implements Serializable {

    @Version
    private Integer version;

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String surname;

    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Book> books;

}
