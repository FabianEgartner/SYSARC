package eventside.rest;

import eventside.infrastructure.EventRepository;
import eventside.domain.Event;
import eventside.domain.api.BookingSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

//    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
//    public boolean addEvent(@RequestBody Event event) {
//        // TODO: process event in repository
//        repository.processEvent(event);
//        System.out.println("Event received: " + event);
//        return true;
//    }

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean bookRoom(@RequestBody Event event) {
        System.out.println("[EventSide] Event received: " + event);
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/subscribed", consumes = "application/json", produces = "application/json")
    public boolean subscribe(@RequestBody BookingSubscription bookingSubscription) {
        System.out.println("EventRestController: /subscribed");
        System.out.println(bookingSubscription);
//        repository.addBookingListener(bookingSubscription);
        return true;
    }
}
