package com.snippetapiservice.codeSnippetsApp.Post;

import com.snippetapiservice.codeSnippetsApp.Owner.AuthorRepository;
import com.snippetapiservice.codeSnippetsApp.Post.PostNotFoundException;
import com.snippetapiservice.codeSnippetsApp.Post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


    @RestController
    @RequestMapping("/api/v1")
    public class PostsController {

        @Autowired
        private final PostRepository postRepository;

        @Autowired
        private final AuthorRepository authorRepository;

        public PostsController(PostRepository postRepository, AuthorRepository authorRepository) {
            this.postRepository = postRepository;
            this.authorRepository = authorRepository;
        }

        @GetMapping("/posts")
        List<Post> getAll() {
            return postRepository.findAll();
        }

        @PostMapping("/post")
        Post savePost(@RequestBody Post newPost) {
            return postRepository.save(newPost);
        }

        @GetMapping("/post/{id}")
        Post getById(@PathVariable Long id) {

            return postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFoundException(id));
        }

        @PutMapping("/update/{id}")
        Post replacePost(@RequestBody Post newPost, @PathVariable Long id) {

            return postRepository.findById(id)
                    .map(post -> {
                        post.setCreatedDate(newPost.getCreatedDate());
                        post.setModifiedDate(newPost.getModifiedDate());
                        post.setPostTitle(newPost.getPostTitle());
                        post.setPostType(newPost.getPostType());
                        post.setPostContent(newPost.getPostContent());

                        return postRepository.save(post);
                    })
                    .orElseGet(() -> {
                        newPost.setPostId(id);
                        return postRepository.save(newPost);
                    });
        }

        @PutMapping("/modify/{id}")
        Post modifyPost(@RequestBody Post newPost, @PathVariable Long id) {

            return postRepository.findById(id)
                    .map(post -> {
                        post.setCreatedDate(newPost.getCreatedDate());
                        post.setModifiedDate(newPost.getModifiedDate());
                        post.setPostTitle(newPost.getPostTitle());
                        post.setPostType(newPost.getPostType());
                        post.setPostContent(newPost.getPostContent());

                        return postRepository.save(post);
                    })
                    .orElseGet(() -> {
                        newPost.setPostId(id);
                        return postRepository.save(newPost);
                    });
        }

        @DeleteMapping("/post/{id}")
        void deletePost(@PathVariable Long id) {
            postRepository.deleteById(id);
        }
    }

