package com.baridmedia.trainink.services;

import com.baridmedia.trainink.dtos.BookResponse;
import com.baridmedia.trainink.entities.Book;
import com.baridmedia.trainink.repositories.AuthorRepository;
import com.baridmedia.trainink.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository books;
    private final AuthorRepository authors;
    public BookService(BookRepository books, AuthorRepository authors) { this.books = books; this.authors = authors; }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() { return books.findAll().stream().map(BookResponse::from).toList(); }
    @Transactional(readOnly = true)
    public List<BookResponse> findByAuthor(Long authorId) {
        requireAuthor(authorId);
        return books.findByAuthorId(authorId).stream().map(BookResponse::from).toList();
    }
    @Transactional(readOnly = true)
    public BookResponse findById(Long id) { return BookResponse.from(requireBook(id)); }
    public BookResponse create(String title, Long authorId) {
        var author = requireAuthor(authorId);
        var book = new Book(title);
        author.addBook(book);
        return BookResponse.from(books.save(book));
    }
    public BookResponse update(Long id, String title, Long authorId) {
        var book = requireBook(id);
        book.setTitle(title);
        book.changeAuthor(requireAuthor(authorId));
        return BookResponse.from(book);
    }
    public void delete(Long id) { books.delete(requireBook(id)); }

    private Book requireBook(Long id) {
        return books.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " was not found"));
    }
    private com.baridmedia.trainink.entities.Author requireAuthor(Long id) {
        return authors.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author " + id + " was not found"));
    }
}
