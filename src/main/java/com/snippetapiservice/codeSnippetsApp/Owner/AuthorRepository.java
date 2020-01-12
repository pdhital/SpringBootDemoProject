package com.snippetapiservice.codeSnippetsApp.Owner;

import com.snippetapiservice.codeSnippetsApp.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    Author save(Author author);

    List<Author> findByFirstName(String firstName);
}




