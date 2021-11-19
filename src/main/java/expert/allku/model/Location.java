package expert.allku.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {

    public Location() { }

    public Location(String name, String observation, String status) {
        this.name = name;
        this.observation = observation;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, columnDefinition = "varchar")
    private String name;

    @NotNull
    @Column(name = "observation", nullable = false, columnDefinition = "varchar")
    private String observation;

    @NotNull
    @Column(name = "status", nullable = false, columnDefinition = "varchar")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "location_locations_fkey"))
    private Location parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Location> children;

    @OneToMany(mappedBy = "location",
            fetch= FetchType.EAGER)
    private Set<Beer> beers = new HashSet<>();

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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonBackReference
    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    public Set<Location> getChildren() {
        return children;
    }

    public void setChildren(Set<Location> children) {
        this.children = children;
    }

    public Set<Beer> getBeers() {
        return beers;
    }

    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }
}
