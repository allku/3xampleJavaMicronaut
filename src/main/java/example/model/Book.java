package example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    public Book() { }

    public Book(@NotNull String name, @NotNull String editorial, @NotNull String published) {
        this.name = name;
        this.editorial = editorial;
        this.published = published;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, length=100)
    private String name;

    @NotNull
    @Column(name = "editorial", nullable = false, length=150)
    private String editorial;

    @NotNull
    @Column(name = "published", nullable = false, length=100)
    private String published;

    @OneToMany(mappedBy = "book",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch= FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Author> authors = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @JsonManagedReference
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", editorial='" + editorial + '\'' +
                ", published=" + published +
                '}';
    }
}
