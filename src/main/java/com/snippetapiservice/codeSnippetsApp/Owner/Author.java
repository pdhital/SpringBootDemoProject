package com.snippetapiservice.codeSnippetsApp.Owner;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.snippetapiservice.codeSnippetsApp.Post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Address address;

    @OneToMany(mappedBy="author")
    @JsonManagedReference
    protected List<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getAuthorId(), author.getAuthorId()) &&
                Objects.equals(getFirstName(), author.getFirstName()) &&
                Objects.equals(getLastName(), author.getLastName()) &&
                Objects.equals(getEmail(), author.getEmail()) &&
                Objects.equals(getAddress(), author.getAddress()) &&
                Objects.equals(getPosts(), author.getPosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorId(), getFirstName(), getLastName(), getEmail(), getAddress(), getPosts());
    }
}
