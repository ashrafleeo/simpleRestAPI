package com.baridmedia.trainink.controllers;

import com.baridmedia.trainink.dtos.AuthorRequest;
import com.baridmedia.trainink.dtos.AuthorResponse;
import com.baridmedia.trainink.dtos.BookResponse;
import com.baridmedia.trainink.services.AuthorService;
import com.baridmedia.trainink.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authors;
    private final BookService books;
    public AuthorController(AuthorService authors, BookService books) { this.authors = authors; this.books = books; }

    @GetMapping
    public List<AuthorResponse> list() { return authors.findAll(); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse create(@Valid @RequestBody AuthorRequest request) { return authors.create(request.name()); }
    @GetMapping("/{id}")
    public AuthorResponse get(@PathVariable Long id) { return authors.findById(id); }
    @GetMapping("/{id}/books")
    public List<BookResponse> listBooks(@PathVariable Long id) { return books.findByAuthor(id); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { authors.delete(id); }
}
