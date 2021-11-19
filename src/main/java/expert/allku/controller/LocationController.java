package expert.allku.controller;

import expert.allku.model.Location;
import expert.allku.repository.LocationRepository;
import io.micronaut.http.annotation.*;

@Controller("/rest/v1")
public class LocationController {

    protected final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Get(value = "/location/{id}")
    public Location show(Integer id) {
        var location = locationRepository
                .findById(id).orElse(null);
        return location;
    }

    @Get(value = "/locationbyname/{name}")
    public Location showByName(String name) {
        var location = locationRepository
                .findByName(name).orElse(null);
        return location;
    }
}