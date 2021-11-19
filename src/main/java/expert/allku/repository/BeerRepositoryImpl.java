package expert.allku.repository;

import expert.allku.dto.BeerDto;
import expert.allku.model.Beer;
import expert.allku.model.Ingredient;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class BeerRepositoryImpl implements BeerRepository {

  private final EntityManager entityManager;

  public BeerRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @ReadOnly
  public Optional<Beer> findById(Integer id) {
    return Optional.ofNullable(
            entityManager.find(Beer.class, id)
    );
  }

  @Override
  @ReadOnly
  public List<Beer> findAll() {
    return entityManager.createQuery("from Beer")
            .getResultList();
  }

  @Override
  @Transactional
  public Beer save(BeerDto b) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    var date = new Date();
    try {
      date = formatter.parse(b.getDateReleased());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    var beer = new Beer(b.getName(),
            b.getBrand(),
            date);

    var iterator = b.getIngredients().iterator();
    Set<Ingredient> ingredients = new HashSet<>();

    while (iterator.hasNext()) {
      var i = new Ingredient(iterator.next().getName());
      i.setBeer(beer);
      ingredients.add(i);
    }

    beer.setIngredients(ingredients);

    entityManager.persist(beer);
    return beer;
  }

  @Override
  @Transactional
  public int update(@NotNull Integer id, @NotNull BeerDto b) {
    var beer = entityManager.find(Beer.class, id);
    if (beer == null)
      return 0;
    else {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

      var date = new Date();
      try {
        date = formatter.parse(b.getDateReleased());
      } catch (ParseException e) {
        e.printStackTrace();
      }

      beer.setName(b.getName());
      beer.setBrand(b.getBrand());
      beer.setDateReleased(date);
      entityManager.persist(beer);
    }
    return beer.getId();
  }

  @Override
  @Transactional
  public void delete(@NotNull Integer id) {
    var beer = entityManager.find(Beer.class, id);
    if (beer != null) {
      entityManager.remove(beer);
      entityManager.flush();
      entityManager.clear();
    }
  }
}
