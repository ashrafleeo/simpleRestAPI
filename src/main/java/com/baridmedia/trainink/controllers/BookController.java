package com.baridmedia.trainink.controllers;

import com.baridmedia.trainink.dtos.BookRequest;
import com.baridmedia.trainink.dtos.BookResponse;
import com.baridmedia.trainink.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService books;
    public BookController(BookService books) { this.books = books; }

    @GetMapping
    public List<BookResponse> list() { return books.findAll(); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookRequest request) { return books.create(request.title(), request.authorId()); }
    @GetMapping("/{id}")
    public BookResponse get(@PathVariable Long id) { return books.findById(id); }
    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return books.update(id, request.title(), request.authorId());
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { books.delete(id); }
}
