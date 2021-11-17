package expert.allku;

import expert.allku.dto.BeerDto;
import expert.allku.dto.IngredientDto;
import expert.allku.dto.LocationDto;
import expert.allku.repository.BeerRepository;
import expert.allku.repository.LocationRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;


@Singleton
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    protected final BeerRepository beerRepository;
    protected final LocationRepository locationRepository;

    public DataLoader(
            BeerRepository beerRepository,
            LocationRepository locationRepository) {
        this.beerRepository = beerRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {

        var locationRoot = new LocationDto("Tierra", "", "Activo");

        var america = new LocationDto("America", "", "Activo");
        var europa = new LocationDto("Europa", "", "Activo");

        america.getChildren().add(new LocationDto("Ecuador", "", "Activo"));
        europa.getChildren().add(new LocationDto("Alemania", "", "Activo"));

        locationRoot.getChildren()
                .add(america);
        locationRoot.getChildren()
                .add(europa);

        locationRepository.saveEarthContinentsCountries(locationRoot);

        var beer1 = new BeerDto("Calenturienta",
                "Cervezas Jorge Luis",
                "Ecuador",
                "2021-10-27");

        beer1.getIngredients().add(new IngredientDto("Malta"));
        beer1.getIngredients().add(new IngredientDto("Agua"));
        beer1.getIngredients().add(new IngredientDto("Canela"));

        beerRepository.save(beer1);

        var beer2 = new BeerDto("Pilsener",
                "Cervezas Nacionales",
                "Ecuador",
                "1990-01-01");

        beer2.getIngredients().add(new IngredientDto("Malta"));
        beer2.getIngredients().add(new IngredientDto("Agua"));

        beerRepository.save(beer2);

        System.out.println("Insert beers and ingredients");
    }
}
