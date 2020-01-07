package com.snippetapiservice.codeSnippetsApp.Post;

public class PostNotFoundException  extends RuntimeException {

    PostNotFoundException(Long id) {
        super("Could not find the post " + id);
    }
}