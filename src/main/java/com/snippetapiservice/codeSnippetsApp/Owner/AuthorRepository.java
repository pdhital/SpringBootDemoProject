package com.snippetapiservice.codeSnippetsApp.Owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    Author save(Author author);
}




