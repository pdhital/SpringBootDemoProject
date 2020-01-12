package com.snippetapiservice.codeSnippetsApp.Post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.snippetapiservice.codeSnippetsApp.Owner.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "post")
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    protected Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) &&
                Objects.equals(createdDate, post.createdDate) &&
                Objects.equals(modifiedDate, post.modifiedDate) &&
                Objects.equals(postType, post.postType) &&
                Objects.equals(postTitle, post.postTitle) &&
                Objects.equals(postContent, post.postContent) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, createdDate, modifiedDate, postType, postTitle, postContent, author);
    }
}
