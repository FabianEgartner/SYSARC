package eventside.infrastructure;

import eventside.domain.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();

    private List<Subscriber> subscribers = List.of(new Subscriber("http://localhost:8082"));

    public void processEvent(Event event) {
        events.add(event);

        for (Subscriber subscriber : subscribers) {
            subscriber.notify(event);
        }
    }

    public void addSubscriber(String host) {
        Subscriber subscriber = new Subscriber(host);
        subscribers.add(subscriber);

        for (Event event : events) {
            subscriber.notify(event);
        }
    }

    public void removeSubscriber(String host) {
        subscribers.removeIf(subscriber -> subscriber.getHost().equals(host));
    }

}
