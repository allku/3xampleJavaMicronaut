package expert.allku.repository;

import expert.allku.dto.BeerDtoForList;
import expert.allku.dto.BeerDtoIn;
import expert.allku.dto.BeerDtoOut;
import expert.allku.model.Beer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface IBeerRepo {

    Optional<BeerDtoOut> findById(@NotNull Integer id);
    Optional<Beer> findByIdEasy(@NotNull Integer id);
    List<BeerDtoForList> findAll();
    Beer save(@NotBlank BeerDtoIn b);
    int update(@NotNull Integer id, @NotBlank BeerDtoIn b);
    void delete(@NotNull Integer id);
}
