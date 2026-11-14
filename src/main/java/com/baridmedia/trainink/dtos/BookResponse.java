package com.baridmedia.trainink.dtos;

import com.baridmedia.trainink.entities.Book;

public record BookResponse(Long id, String title, Long authorId, String authorName) {
    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getAuthor().getName());
    }
}
