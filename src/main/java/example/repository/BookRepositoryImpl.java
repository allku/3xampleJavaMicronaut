package example.repository;

import example.ApplicationConfiguration;
import example.SortingAndOrderArguments;
import example.dto.BookDto;
import example.model.Author;
import example.model.Book;
import io.micronaut.transaction.annotation.ReadOnly;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Singleton
public class BookRepositoryImpl implements BookRepository {

    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public BookRepositoryImpl(EntityManager entityManager,
                              ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @ReadOnly
    public Optional<Book> findById(@NotNull Integer id) {
        return Optional
                .ofNullable(entityManager.find(Book.class, id));

//        try {
//            var result = Optional
//                    .ofNullable(entityManager
//                            .createQuery("select b from Book b " +
//                                    "join b.authors a " +
//                                    "where b.id = :id", Book.class)
//                            .setParameter("id", id)
//                            .getSingleResult());
//
//            if(!result.isEmpty())
//                return result;
//
//        } catch (NoResultException e) {
//            e.printStackTrace();
//            return Optional.empty();
//        }
//        return Optional.empty();
    }

    @Override
    @Transactional
    public Book save(@NotBlank BookDto b) {
        Book book = new Book(b.getName(),
                b.getEditorial(),
                b.getPublished());

        var iterator = b.getAuthors().iterator();
        Set<Author> authors = new HashSet<>();
        while(iterator.hasNext()){
            var a = new Author(iterator.next().getName());
            a.setBook(book);
            authors.add(a);
        }
        book.setAuthors(authors);

        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public int update(@NotNull Integer id, @NotBlank BookDto b) {
        var book = entityManager.find(Book.class, id);

        if (book == null)
            return 0;
        else {
            book.setName(b.getName());
            book.setEditorial(b.getEditorial());
            book.setPublished(b.getPublished());
            entityManager.persist(book);
        }
        return book.getId();
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer id) {
        var book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
            entityManager.flush();
            entityManager.clear();
        }
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");

    @Override
    public List<Book> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT g FROM Book as g";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        TypedQuery<Book> query = entityManager.createQuery(qlString, Book.class);
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }
}
