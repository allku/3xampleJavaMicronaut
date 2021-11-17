package expert.allku.repository;

import expert.allku.dto.LocationDto;
import expert.allku.model.Location;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class LocationRepositoryImpl implements LocationRepository {

    private final EntityManager entityManager;

    public LocationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Location> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Location saveEarthContinentsCountries(LocationDto l) {

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
