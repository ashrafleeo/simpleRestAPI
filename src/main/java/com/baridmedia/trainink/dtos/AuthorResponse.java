package com.baridmedia.trainink.dtos;

import com.baridmedia.trainink.entities.Author;
import java.util.List;

public record AuthorResponse(Long id, String name, List<BookSummary> books) {
    public record BookSummary(Long id, String title) {}
    public static AuthorResponse from(Author author) {
        return new AuthorResponse(author.getId(), author.getName(), author.getBooks().stream()
                .map(book -> new BookSummary(book.getId(), book.getTitle())).toList());
    }
}
