package eventside.infrastructure;

import eventside.domain.Event;
import eventside.domain.api.EventRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepositoryImpl implements EventRepository {

    private final List<Event> events = new ArrayList<>();
    private List<Subscriber> subscribers = List.of(new Subscriber("http://localhost:8082"));
    private static EventRepositoryImpl instance;

    public static EventRepositoryImpl getInstance()
    {
        if (null == EventRepositoryImpl.instance) {
            new EventRepositoryImpl();
        }

        return EventRepositoryImpl.instance;
    }

    private EventRepositoryImpl() {
        EventRepositoryImpl.instance = this;
    }

    @Override
    public void processEvent(Event event) {
        events.add(event);

        for (Subscriber subscriber : subscribers) {
            subscriber.notify(event);
        }
    }

    @Override
    public void addSubscriber(String host) {
        Subscriber subscriber = new Subscriber(host);
        subscribers.add(subscriber);

        for (Event event : events) {
            subscriber.notify(event);
        }
    }

    @Override
    public void removeSubscriber(String host) {
        subscribers.removeIf(subscriber -> subscriber.getHost().equals(host));
    }

}
