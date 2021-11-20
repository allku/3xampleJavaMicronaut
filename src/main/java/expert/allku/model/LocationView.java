package expert.allku.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Table(name = "v_locations")
@Immutable
public class LocationView {

    public LocationView() { }

    @Id
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "location", columnDefinition = "varchar")
    private String location;

    @Column(name = "level")
    private Integer level;

    @Column(name = "observation", columnDefinition = "varchar")
    private String observation;

    @Column(name = "status", columnDefinition = "varchar")
    private String status;

    @Column(name = "location_id")
    private Integer location_id;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }
}
