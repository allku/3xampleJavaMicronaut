package expert.allku.repository;

import expert.allku.dto.LocationDto;
import expert.allku.model.Location;
import expert.allku.model.LocationView;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Singleton
public class LocationRepositoryImpl implements LocationRepository {

    private final EntityManager entityManager;

    public LocationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public Optional<Location> findById(Integer id) {
        return Optional.ofNullable(
                entityManager.find(Location.class, id)
        );
    }

    @Override
    @ReadOnly
    public Optional<LocationView> findViewById(Integer id) {
        return Optional.ofNullable(
                entityManager.find(LocationView.class, id)
        );
    }

    @Override
    @ReadOnly
    public Optional<Location> findByName(String name) {

        return Optional.ofNullable(
                (Location) entityManager
                        .createQuery("from Location " +
                                "where name = :name")
                        .setParameter("name", name)
                        .getSingleResult()
        );
    }

    @Override
    @ReadOnly
    public List<Location> findAll() {
        return entityManager.createQuery("from Location")
                .getResultList();
    }

    @Override
    @Transactional
    public Location save(LocationDto l) {

        var mainLocation = new Location(
                l.getName(),
                l.getObservation(),
                l.getStatus());

        mainLocation = assignLocations(l.getChildren().iterator(), mainLocation);

        entityManager.persist(mainLocation);
        return mainLocation;
    }

    /**
     * Recursive method to assign location data transfer to location model
     * @param locationDataIterator
     * @param parent
     * @return
     */
    private Location assignLocations(Iterator<LocationDto> locationDataIterator, Location parent) {

        Set<Location> locationChildren = new HashSet<>();

        while (locationDataIterator.hasNext()) {
            var locationData = locationDataIterator.next();

            var location = new Location(
                    locationData.getName(),
                    locationData.getObservation(),
                    locationData.getStatus()
            );
            location = assignLocations(
                    locationData.getChildren().iterator(),
                    location
            );
            location.setParent(parent);
            locationChildren.add(location);
        }

        if (!locationChildren.isEmpty())
            parent.setChildren(locationChildren);

        return parent;
    }

    /**
     * Method for save location in model location, only support 3 levels
     * @param l
     * @return
     */
    @Override
    @Transactional
    public Location saveEarthContinentsCountriesDeprecated(LocationDto l) {

        var locationEarth = new Location(
                l.getName(),
                l.getObservation(),
                l.getStatus());

        var iteratorContinent = l.getChildren().iterator();
        Set<Location> continents = new HashSet<>();

        while (iteratorContinent.hasNext()) {
            var iteratorLocationContinent = iteratorContinent.next();

            var i = new Location(
                    iteratorLocationContinent.getName(),
                    iteratorLocationContinent.getObservation(),
                    iteratorLocationContinent.getStatus()
            );
            var iteratorCountry = iteratorLocationContinent.getChildren().iterator();
            Set<Location> countries = new HashSet<>();

            while (iteratorCountry.hasNext()) {
                var iteratorLocationCountry = iteratorCountry.next();

                var j = new Location(iteratorLocationCountry.getName(),
                        iteratorLocationCountry.getObservation(),
                        iteratorLocationCountry.getStatus());

                j.setParent(i);
                countries.add(j);
            }
            i.setChildren(countries);
            i.setParent(locationEarth);
            continents.add(i);
        }

        locationEarth.setChildren(continents);

        entityManager.persist(locationEarth);
        return locationEarth;
    }
}
