package eventside.rest;

import eventside.EventRepository;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        // TODO: process event in repository
        System.out.println("TEST");
        repository.processEvent(event);
        System.out.println("Event received: " + event);
        return true;
    }

    @PostMapping(value = "/bookRoom", consumes = "application/json", produces = "application/json")
    public void bookRoom(@RequestBody Event event) {
        System.out.println("TEST");
    }
}
