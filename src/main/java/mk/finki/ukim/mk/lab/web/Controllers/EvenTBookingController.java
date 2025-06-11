package mk.finki.ukim.mk.lab.web.Controllers;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EvenTBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;

    public EvenTBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }
    @GetMapping("/eventBooking/{id}")
    public String eventBooking(@PathVariable Long id, Model model) throws Exception {
        EventBooking eventBooking = this.eventBookingService.getBooking(this.eventService.findById(id).orElseThrow(Exception::new).getName());
        model.addAttribute("eventBooking",eventBooking);
        return "bookingConfirmation";
    }
}
