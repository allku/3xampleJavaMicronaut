package expert.allku.repository;

import expert.allku.dto.LocationDto;
import expert.allku.model.Location;
import io.micronaut.data.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findById(@NotNull Integer id);
    Location findByName(@NotNull String name);
    List<Location> findAll();
    Location saveEarthContinentsCountries(@NotBlank LocationDto l);
}