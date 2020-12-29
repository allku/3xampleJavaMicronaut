package example.dto;

import example.model.Author;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class BookDto {

    public BookDto() { }

    public BookDto(@NotBlank String name, @NotBlank String editorial, @NotBlank String published) {
        this.name = name;
        this.editorial = editorial;
        this.published = published;
    }

    @NotBlank
    private String name;

    @NotBlank
    private String editorial;

    @NotBlank
    private String published;

    private Set<AuthorDto> authors = new HashSet<>();

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

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }
}
