package expert.allku.repository;

import expert.allku.dto.BeerDtoForList;
import expert.allku.dto.BeerDtoIn;
import expert.allku.dto.BeerDtoOut;
import expert.allku.dto.IngredientDtoOut;
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
public class BeerRepo implements IBeerRepo {

    private final EntityManager entityManager;
    protected final ILocationRepo ILocationRepo;

    public BeerRepo(EntityManager entityManager, ILocationRepo ILocationRepo) {
        this.entityManager = entityManager;
        this.ILocationRepo = ILocationRepo;
    }

    @Override
    @ReadOnly
    public Optional<BeerDtoOut> findById(Integer id) {
        var beer = entityManager.find(Beer.class, id);

        if (beer == null) return Optional.empty();

        var beerOut = new BeerDtoOut(
                beer.getId(),
                beer.getName(),
                beer.getBrand(),
                beer.getDateReleased()
        );

        if (beer.getLocation() != null) {
            var location = ILocationRepo.findViewById(beer.getLocation().getId());

            if (location.isPresent()) {
                beerOut.setLocationId(location.get().getId());
                beerOut.setOrigin(location.get().getName());
                beerOut.setLocation(location.get().getLocation());
            }
        }

        var iterator = beer.getIngredients().iterator();
        while (iterator.hasNext()) {
            var ingredient = iterator.next();

            beerOut.getIngredients()
                    .add(new IngredientDtoOut(
                            ingredient.getId(),
                            ingredient.getName()));
        }

        return Optional.ofNullable(beerOut);
    }

    @Override
    public Optional<Beer> findByIdEasy(Integer id) {
        return Optional.ofNullable(entityManager.find(Beer.class, id));
    }

    @Override
    @ReadOnly
    public List<BeerDtoForList> findAll() {
        List<Beer> beers = entityManager.createQuery("from Beer")
                .getResultList();

        List<BeerDtoForList> beerDtoForListing = new ArrayList<>();
        var iterator = beers.iterator();
        while (iterator.hasNext()) {
            var beer = new BeerDtoForList();
            var b = iterator.next();
            beer.setId(b.getId());
            beer.setName(b.getName());
            beer.setBrand(b.getBrand());
            beer.setDateReleased(b.getDateReleased());

            if (b.getLocation() != null) {
                beer.setLocationId(b.getLocation().getId());
                var location = ILocationRepo.findViewById(b.getLocation().getId());

                if (location.isPresent()) {
                    beer.setLocation(location.get().getLocation());
                    beer.setOrigin(location.get().getName());
                }
            }

            beerDtoForListing.add(beer);
        }

        return beerDtoForListing;
    }

    @Override
    @Transactional
    public Beer save(BeerDtoIn b) {
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

        // Verify if exist location id in database
        if (b.getLocationId() != null) {
            var location = ILocationRepo
                    .findById(b.getLocationId());
            // If exist then assign location
            if (location.isPresent())
                beer.setLocation(location.get());
        }

        entityManager.persist(beer);
        return beer;
    }

    @Override
    @Transactional
    public int update(@NotNull Integer id, @NotNull BeerDtoIn b) {
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
