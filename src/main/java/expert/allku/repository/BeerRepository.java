package expert.allku.repository;

import expert.allku.dto.BeerDto;
import expert.allku.model.Beer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BeerRepository {

    Optional<Beer> findById(@NotNull Integer id);
    List<Beer> findAll();
    Beer save(@NotBlank BeerDto b);
    int update(@NotNull Integer id, @NotBlank BeerDto b);
    void delete(@NotNull Integer id);
}
