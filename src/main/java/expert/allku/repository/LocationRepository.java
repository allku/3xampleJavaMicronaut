package expert.allku.repository;

import expert.allku.dto.LocationDto;
import expert.allku.model.Location;
import expert.allku.model.LocationView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findById(@NotNull Integer id);
    Optional<LocationView> findViewById(@NotNull Integer id);
    Optional<Location> findByName(@NotNull String name);
    List<Location> findAll();
    Location save(@NotBlank LocationDto l);
    Location saveEarthContinentsCountriesDeprecated(@NotBlank LocationDto l);
}