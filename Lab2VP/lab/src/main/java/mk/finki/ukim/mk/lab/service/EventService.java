package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EventService {
    public Optional<Event> update(Long id, String name, String description, double popularityScore, Long locationId);
    List<Event> findAllByLocationId(Long locationId);
    List<Event> listAll();
    Optional<Event> findById(Long id);
    List<Event> searchEvents(String text);
//    List<Event> searchEventsByRating(Double rating);
//    List<Event> searchEventsByNameAndRating(String name,Double rating);
    void deleteById(Long id);
    Optional<Event> save(String name, String description, double popularityScore,
                         Long location);
    void deleteEventsByLocation(Long location);

}
