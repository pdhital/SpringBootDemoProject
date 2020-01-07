package com.snippetapiservice.codeSnippetsApp.Owner;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.snippetapiservice.codeSnippetsApp.Post.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id @GeneratedValue
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Address address;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    protected List<Post> posts;

    public Author() {
    }

    public Author( Long authorId, String firstName, String lastName, String email) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
