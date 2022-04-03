package eventside.rest;

import eventside.infrastructure.EventRepositoryImpl;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    @Autowired
    private EventRepositoryImpl repository;

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean bookRoom(@RequestBody Event event) {
        System.out.println("[EventSide] Event received: " + event);
        repository.processEvent(event);
        return true;
    }

}
