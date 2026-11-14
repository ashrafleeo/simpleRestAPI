package com.baridmedia.trainink.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    protected Book() {}
    public Book(String title) { this.title = title; }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public void setTitle(String title) { this.title = title; }
    void setAuthor(Author author) { this.author = author; }
    public void changeAuthor(Author newAuthor) {
        if (author != null) author.removeBook(this);
        newAuthor.addBook(this);
    }
}
