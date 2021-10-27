package expert.allku;

import expert.allku.dto.BeerDto;
import expert.allku.dto.IngredientDto;
import expert.allku.repository.BeerRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    protected final BeerRepository beerRepository;

    public DataLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
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
