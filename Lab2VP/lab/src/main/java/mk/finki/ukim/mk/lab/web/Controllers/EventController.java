package mk.finki.ukim.mk.lab.web.Controllers;


import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false)  String searchName,
                                 @RequestParam(required = false) String minRating,
                                 @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> eventList;
//        if (searchName != null && minRating != null && !Objects.equals(minRating, "")) {
//            eventList = eventService.searchEventsByNameAndRating(searchName,Double.parseDouble(minRating));
//        } else if (minRating != null && !Objects.equals(minRating, "")) {
//            eventList = eventService.searchEventsByRating(Double.parseDouble(minRating));}
          if (searchName != null) {
            eventList = eventService.searchEvents(searchName);
        } else {
            eventList = eventService.listAll();
        }
        model.addAttribute("eventList", eventList);
        return "listEvents";

    }
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
         this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = this.locationService.findAll();


            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }
//    @GetMapping("/eventsByLocation")
//    public String filterEventsByLocation(Model model,@RequestParam(required = false)  Long locationId)
//    {
//        List<Event> events;
//        List<Location> locations = this.locationService.findAll();
//        if(locationId!=null)
//        {
//            events=this.eventService.findAllByLocationId(locationId);
//        }
//        else{
//         events = this.eventService.listAll();}
//        model.addAttribute("locations", locations);
//        model.addAttribute("events",events);
//        return "filterEvent";
//    }
    @GetMapping("/add-form")
    public String addEventPage(Model model) {

        List<Location> locations = this.locationService.findAll();

        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Double popularityScore,
                              @RequestParam Long location) {
        if(id!=null)
        {
            this.eventService.update(id,name, description, popularityScore, location);
        }
        else {
            this.eventService.save(name, description, popularityScore, location);
        }
        return "redirect:/events";
    }


    @PostMapping("/placeBooking")
    public String placeEventBooking(@RequestParam Long eventId,
                                    @RequestParam Long numTickets,
                                    HttpServletRequest request) {
        // Get the username and address of the attendee
        String attendeeName = "ANA";
        String attendeeAddress = request.getRemoteAddr();

        // Store event name in session


        // Call the booking service with parsed ticket count
        eventBookingService.placeBooking(this.eventService.findById(eventId).get().getName(), attendeeName, attendeeAddress, numTickets);

        // Redirect to the event booking page
        return "redirect:/eventBooking/" + eventId;
    }

    //


//    @GetMapping("/add-form")
//    public String addProductPage(Model model) {
//        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("manufacturers", manufacturers);
//        model.addAttribute("categories", categories);
//        return "add-product";
//    }

//    @PostMapping("/add")
//    public String saveProduct(@RequestParam String name,
//                              @RequestParam Double price,
//                              @RequestParam Integer quantity,
//                              @RequestParam Long category,
//                              @RequestParam Long manufacturer) {
//        this.productService.save(name, price, quantity, category, manufacturer);
//        return "redirect:/products";
//    }
}


