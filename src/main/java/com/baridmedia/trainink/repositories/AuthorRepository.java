package com.baridmedia.trainink.repositories;

import com.baridmedia.trainink.entities.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Override @EntityGraph(attributePaths = "books")
    List<Author> findAll();
    @Override @EntityGraph(attributePaths = "books")
    Optional<Author> findById(Long id);
}
