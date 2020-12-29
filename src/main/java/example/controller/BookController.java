package example.controller;

import example.SortingAndOrderArguments;
import example.dto.BookDto;
import example.model.Book;
import example.repository.BookRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/rest/v1/books")
public class BookController {

    protected final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @Get("/{id}")
    public Book show(Integer id) {
        var book = bookRepository
                .findById(id).orElse(null);
        return book;
    }

    @Get(value = "/list{?args*}")
    public List<Book> list(@Valid SortingAndOrderArguments args) {
        return bookRepository.findAll(args);
    }

    @Post
    public HttpResponse<Book> save(@Body @Valid BookDto bookDto) {
        Book book = bookRepository.save(bookDto);

        return HttpResponse
                .created(book)
                .headers(headers -> headers.location(location(book.getId())));
    }

    @Put("/{id}")
    public HttpResponse update(Integer id, @Body @Valid BookDto bookDto) {
        bookRepository.update(id, bookDto);
        return HttpResponse
                .noContent()
                .headers(headers -> headers.location(location(id)));
    }

    @Delete("/{id}")
    public HttpResponse delete(Integer id) {
        bookRepository.delete(id);
        return HttpResponse.noContent();
    }

    protected URI location(Integer id) {
        return URI.create("/books/" + id);
    }
}
