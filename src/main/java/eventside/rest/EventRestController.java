package eventside.rest;

import eventside.infrastructure.EventRepositoryImpl;
import eventside.domain.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    private final EventRepositoryImpl repository = EventRepositoryImpl.getInstance();

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean createBooking(@RequestBody Event event) {
        System.out.println("[EventSide] Event received: " + event);
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/bookingCancelled", consumes = "application/json", produces = "application/json")
    public boolean cancelBooking(@RequestBody Event event) {
        System.out.println("[EventSide] Event received: " + event);
        repository.processEvent(event);
        return true;
    }

}
