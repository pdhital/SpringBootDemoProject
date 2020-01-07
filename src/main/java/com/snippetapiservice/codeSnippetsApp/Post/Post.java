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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @NotNull
    @JsonBackReference
    protected Author author;


}
