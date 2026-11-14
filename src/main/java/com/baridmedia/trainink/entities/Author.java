package com.baridmedia.trainink.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    protected Author() {}
    public Author(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
    public void setName(String name) { this.name = name; }
    public void addBook(Book book) { books.add(book); book.setAuthor(this); }
    void removeBook(Book book) { books.remove(book); }
}
