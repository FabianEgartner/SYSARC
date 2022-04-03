package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import readside.application.api.BookingServiceRead;
import readside.application.api.RoomServiceRead;
import readside.rest.api.Projection;

@RestController
public class ReadRestController {

    @Autowired
    BookingServiceRead bookingService;

    @Autowired
    RoomServiceRead roomService;

    @Autowired
    Projection projection;

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean bookingCreated(@RequestBody BookingCreatedEvent event) {
        System.out.println("[ReadSide] Event received: " + event);
        projection.processEvent(event);

        return true;
    }

    @PostMapping(value = "/bookingCancelled", consumes = "application/json", produces = "application/json")
    public boolean bookingCancelled(@RequestBody BookingCancelledEvent event) {
        System.out.println("[ReadSide] Event received: " + event);
        projection.processEvent(event);

        return true;
    }
}
