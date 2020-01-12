package com.snippetapiservice.codeSnippetsApp.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostTypeIs(String postType);

}