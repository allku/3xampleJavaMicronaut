package expert.allku.controller;

import expert.allku.model.Location;
import expert.allku.model.LocationView;
import expert.allku.repository.ILocationRepo;
import io.micronaut.http.annotation.*;

@Controller("/rest/v1")
public class LocationController {

    protected final ILocationRepo ILocationRepo;

    public LocationController(ILocationRepo ILocationRepo) {
        this.ILocationRepo = ILocationRepo;
    }

    @Get(value = "/location/{id}")
    public LocationView show(Integer id) {
        var location = ILocationRepo
                .findViewById(id).orElse(null);
        return location;
    }

    @Get(value = "/locationbyname/{name}")
    public Location showByName(String name) {
        var location = ILocationRepo
                .findByName(name).orElse(null);
        return location;
    }
}