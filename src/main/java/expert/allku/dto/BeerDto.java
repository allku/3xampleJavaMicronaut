package expert.allku.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class BeerDto {

  @NotBlank
  private String name;

  @NotBlank
  private String brand;

  @NotBlank
  private String dateReleased;

  @NotBlank
  private Integer locationId;

  private Set<IngredientDto> ingredients = new HashSet<>();

  public BeerDto() { }

  public BeerDto(String name, String brand, String dateReleased) {
    this.name = name;
    this.brand = brand;
    this.dateReleased = dateReleased;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDateReleased() {
    return dateReleased;
  }

  public void setDateReleased(String dateReleased) {
    this.dateReleased = dateReleased;
  }

  public Set<IngredientDto> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Set<IngredientDto> ingredients) {
    this.ingredients = ingredients;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }
}
