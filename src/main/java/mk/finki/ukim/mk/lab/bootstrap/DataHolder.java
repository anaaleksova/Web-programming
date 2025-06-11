package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> events = null;
    public static List<EventBooking> eventBookings = null;
    public static List<Location> locations = null;

    // On application startup, initialize the categories list
    // On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        locations.add(new Location("City Library", "123 Main St, Cityville", "150", "A cozy library with spacious reading areas and conference rooms"));
        locations.add(new Location("Community Center", "456 Maple Ave, Townland", "200", "Multi-purpose hall for social gatherings, workshops, and community meetings"));
        locations.add(new Location("Riverside Park Pavilion", "789 River Rd, Rivertown", "300", "An open pavilion located by the river, ideal for outdoor events and picnics"));
        locations.add(new Location("Historic Theater", "101 Theatre Ln, Oldtown", "120", "A historic theater with vintage decor, perfect for performances and lectures"));
        locations.add(new Location("Tech Hub", "202 Innovation Dr, Silicon Valley", "80", "A modern workspace designed for tech meetups and startup events"));
        locations.add(new Location("Art Gallery", "303 Gallery Blvd, Art District", "100", "Contemporary art gallery featuring local artists and temporary exhibits"));
        events = new ArrayList<>();
        events.add(new Event("Concert A", "Live concert featuring popular bands", 8.7, locations.get(0)));
        events.add(new Event("Movie Night", "Screening of recent blockbuster movies", 6.3,locations.get(1)));
        events.add(new Event("Theater Show", "Broadway-style theater performance", 7.4,locations.get(2)));
        events.add(new Event("Sports Event", "Local football tournament finals", 8.9,locations.get(3)));
        events.add(new Event("Art Exhibition", "Modern art exhibit with interactive art", 7.5,locations.get(4)));


        eventBookings= new ArrayList<>();



    }}
