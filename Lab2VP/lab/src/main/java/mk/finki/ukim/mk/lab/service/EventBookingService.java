package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Service;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets);
    EventBooking getBooking(String eventName);
}
