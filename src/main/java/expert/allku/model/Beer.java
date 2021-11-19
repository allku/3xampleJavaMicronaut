package expert.allku.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beers")
public class Beer {

    public Beer() { }

    public Beer(@NotNull String name,
                @NotNull String brand,
                @NotNull Date dateReleased) {
        this.name = name;
        this.brand = brand;
        this.dateReleased = dateReleased;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, columnDefinition = "varchar")
    private String name;

    @NotNull
    @Column(name = "brand", nullable = false, columnDefinition = "varchar")
    private String brand;

    @NotNull
    @Column(name = "date_released", nullable = false)
    private Date dateReleased;

    @OneToMany(mappedBy = "beer",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true,
            fetch= FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "location_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "location_beers_fkey"))
    private Location location;

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

    public Date getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(Date dateReleased) {
        this.dateReleased = dateReleased;
    }

    @JsonManagedReference
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Beer {" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", brand ='" + brand + '\'' +
                ", dateReleased =" + dateReleased +
                '}';
    }
}