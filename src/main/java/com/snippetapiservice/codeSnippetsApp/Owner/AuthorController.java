package com.snippetapiservice.codeSnippetsApp.Owner;

import com.snippetapiservice.codeSnippetsApp.Post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRipository;

    public AuthorController(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRipository = postRepository;
    }

    @PostMapping("/author")
    Author saveAuthor(@RequestBody Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @GetMapping("/authors")
    List<Author> all() {
        return authorRepository.findAll();
    }

//    @GetMapping("/author/{id}")
//    List<Author> getAuthor(@PathVariable Long id) {
//        List<Author> author = new ArrayList<>();
//        authorRepository
//    }

    @GetMapping("/author/{name}")
    List<Author> getAuthor(@PathVariable String name) {
        return authorRepository.findByFirstName(name);
    }
}
