package example.repository;

import example.SortingAndOrderArguments;
import example.dto.BookDto;
import example.model.Book;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(@NotNull Integer id);
    List<Book> findAll(@NotNull SortingAndOrderArguments args);
    Book save(@NotBlank BookDto b);
    int update(@NotNull Integer id, @NotBlank BookDto b);
    void delete(@NotNull Integer id);
}
