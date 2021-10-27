package expert.allku.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    public Ingredient() { }

    public Ingredient(@NotNull String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, columnDefinition = "varchar")
    private String name;

    @ManyToOne
    @JoinColumn(name = "beer_id",
            foreignKey=@ForeignKey(name = "beer_ingredients_fkey"))
    private Beer beer;

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

    @JsonBackReference
    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    @Override
    public String toString() {
        return "Ingredient {" +
                "id =" + id +
                ", name ='" + name +
                '}';
    }
}