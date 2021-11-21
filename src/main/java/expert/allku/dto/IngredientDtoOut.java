package expert.allku.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class IngredientDtoOut {

    private Integer id;

    private String name;

    public IngredientDtoOut() { }

    public IngredientDtoOut(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public IngredientDtoOut(String name) {
        this.name = name;
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
}
