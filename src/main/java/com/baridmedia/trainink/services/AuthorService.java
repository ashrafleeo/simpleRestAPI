package com.baridmedia.trainink.services;

import com.baridmedia.trainink.dtos.AuthorResponse;
import com.baridmedia.trainink.entities.Author;
import com.baridmedia.trainink.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authors;
    public AuthorService(AuthorRepository authors) { this.authors = authors; }

    @Transactional(readOnly = true)
    public List<AuthorResponse> findAll() {
        return authors.findAll().stream().map(AuthorResponse::from).toList();
    }
    @Transactional(readOnly = true)
    public AuthorResponse findById(Long id) { return AuthorResponse.from(requireAuthor(id)); }
    public AuthorResponse create(String name) { return AuthorResponse.from(authors.save(new Author(name))); }
    public void delete(Long id) { authors.delete(requireAuthor(id)); }

    private Author requireAuthor(Long id) {
        return authors.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author " + id + " was not found"));
    }
}
