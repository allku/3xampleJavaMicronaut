package expert.allku.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class BeerDtoOut {

  private Integer id;

  private String name;

  private String brand;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date dateReleased;

  private Integer locationId;

  private Set<IngredientDtoOut> ingredients = new HashSet<>();

  private String origin;

  private String location;

  public BeerDtoOut() { }

  public BeerDtoOut(Integer id,String name, String brand, Date dateReleased) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.dateReleased = dateReleased;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Set<IngredientDtoOut> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Set<IngredientDtoOut> ingredients) {
    this.ingredients = ingredients;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public Date getDateReleased() {
    return dateReleased;
  }

  public void setDateReleased(Date dateReleased) {
    this.dateReleased = dateReleased;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
