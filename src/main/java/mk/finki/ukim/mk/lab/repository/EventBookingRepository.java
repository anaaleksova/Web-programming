package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventBookingRepository {
    public List<EventBooking> findAll(){
        return DataHolder.eventBookings;
    }
    public EventBooking placeBooking(EventBooking eventBooking){
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
    }
    public EventBooking getBooking(String eventName){
       return this.findAll().stream().filter(e->e.getEventName().equals(eventName)).findFirst().orElse(null);
    }
}
