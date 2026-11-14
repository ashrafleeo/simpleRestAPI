package com.baridmedia.trainink.repositories;

import com.baridmedia.trainink.entities.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override @EntityGraph(attributePaths = "author")
    List<Book> findAll();
    @Override @EntityGraph(attributePaths = "author")
    Optional<Book> findById(Long id);
    @EntityGraph(attributePaths = "author")
    List<Book> findByAuthorId(Long authorId);
}
