package mk.finki.ukim.mk.lab.repository;

import jakarta.persistence.ManyToOne;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.jpa.IEventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository  {
    private List<Event> events;

    public List<Event> findAll(){
        return DataHolder.events;
    }
    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


    public List<Event> searchEvents(String text){
        return DataHolder.events.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text)).toList();
    }
    public List<Event> searchEventsByRating(Double rating){
        return DataHolder.events.stream().filter(e -> e.getPopularityScore()>=rating).toList();
    }
    public List<Event> searchEventsByNameAndRating(String name,Double rating){
        return DataHolder.events.stream().filter(e -> e.getName().contains(name) && e.getPopularityScore()>=rating).toList();
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }
//    private String name;
//    private String description;
//    private double popularityScore;
//    @ManyToOne
//    private Location location;
    public Optional<Event> save(String name, String description, double popularityScore,
                                Location location) {
        if (location == null ) {
            throw new IllegalArgumentException();
        }

        Event event = new Event(name, description, popularityScore, location);
        DataHolder.events.removeIf(p -> p.getName().equals(event.getName()));
        DataHolder.events.add(event);
        return Optional.of(event);
    }


}
