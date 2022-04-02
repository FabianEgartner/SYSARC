package eventside.rest;

import eventside.EventRepository;
import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean bookingCreated(@RequestBody BookingCreatedEvent event) {
//        repository.processEvent(event);
        System.out.println("EventRestController: /bookingCreated");
        return true;
    }

    @PostMapping(value = "/bookingCancelled", consumes = "application/json", produces = "application/json")
    public boolean bookingCancelled(@RequestBody BookingCancelledEvent event) {
//        repository.processEvent(event);
        System.out.println("EventRestController: /bookingCancelled");
        return true;
    }

}
