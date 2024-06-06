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
        @NamedQuery(name = "Publisher.findAll", query = "select t from Publisher as t")
})
@Table(name = "PUBLISHER")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
