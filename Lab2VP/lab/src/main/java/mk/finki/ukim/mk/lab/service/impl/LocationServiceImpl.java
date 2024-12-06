package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ILocationRepository;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final ILocationRepository locationRepository;

    public LocationServiceImpl(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.locationRepository.deleteById(id);
    }

    @Override
    public Optional<Location> save(String name, String address, String capacity, String description) {
        Location location = new Location(name, address, capacity, description);
        return Optional.of(locationRepository.save(location));
    }

}
