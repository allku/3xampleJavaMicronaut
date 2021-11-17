package expert.allku.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Introspected
public class LocationDto {

    @NotBlank
    private String name;

    @NotBlank
    private String observation;

    @NotBlank
    private String status;

    private Set<LocationDto> children = new HashSet<>();

    public LocationDto() { }

    public LocationDto(String name, String observation, String status) {
        this.name = name;
        this.observation = observation;
        this.status = status;
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

    public Set<LocationDto> getChildren() {
        return children;
    }

    public void setChildren(Set<LocationDto> children) {
        this.children = children;
    }
}
