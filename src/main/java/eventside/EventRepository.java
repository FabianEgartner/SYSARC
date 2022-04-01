package eventside;

import eventside.domain.Event;
import org.springframework.stereotype.Component;
import readside.domain.api.BookingRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();

    private BookingRepository bookingRepository;

    public void processEvent(Event event) {
        events.add(event);
        // TODO: notify subscribed read repositories
    }
}
