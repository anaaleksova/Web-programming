package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;

    public EventBookingImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        this.eventBookingRepository.placeBooking(eventBooking);
        return eventBooking;
    }

    @Override
    public EventBooking getBooking(String eventName) {
        return eventBookingRepository.getBooking(eventName);
    }
}
