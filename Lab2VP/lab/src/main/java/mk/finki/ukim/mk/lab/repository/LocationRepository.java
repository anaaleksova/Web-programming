package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll(){
      return DataHolder.locations;
    }

    public Optional<Location> findById(Long id) {
        return DataHolder.locations.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
    public void deleteById(Long id) {
        DataHolder.locations.removeIf(i -> i.getId().equals(id));
    }
    public Optional<Location> save( String name, String address,String capacity,String description) {

        Location location = new Location(name,address,capacity,description);
        DataHolder.locations.removeIf(p -> p.getName().equals(location.getName()));
        DataHolder.locations.add(location);
        return Optional.of(location);
    }
}
