package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.IEventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ILocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final IEventRepository eventRepository;
    private final ILocationRepository locationRepository;

    public EventServiceImpl(IEventRepository eventRepository, ILocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public Page<Event> findPage( Integer pageNum, Integer pageSize) {

        return this.eventRepository.findAll(

                PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        );
    }

    @Override
    public List<Event> findAllByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

//    @Override
//    public List<Event> searchEventsByRating(Double rating) {
//        return eventRepository.searchEventsByRating(rating);
//    }
//
//    @Override
//    public List<Event> searchEventsByNameAndRating(String name, Double rating) {
//        return eventRepository.searchEventsByNameAndRating(name,rating);
//    }
    @Override
    public Optional<Event> update(Long id, String name, String description, double popularityScore, Long locationId) {
        Event event = eventRepository.findById(id).orElse(null);
        Location location = locationRepository.findById(locationId).orElse(null);

        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);

            return Optional.of(this.eventRepository.save(event));
        }

        return Optional.empty();
    }
    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> save(String name, String description, double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location != null) {
            Event event = new Event(name, description, popularityScore, location);
            return Optional.of(eventRepository.save(event));
        }
        return Optional.empty();
    }

    @Override
    public void deleteEventsByLocation(Long location) {
        Location location1 = this.locationRepository.findById(location)
                .orElseThrow(() -> new LocationNotFoundException(location));
        List<Event> eventList = this.eventRepository.findAll().stream().filter(e -> e.getLocation().equals(location1)).toList();
        eventList.forEach(e->this.deleteById(e.getId()));
    }
}
