package eventside.domain.api;

import eventside.domain.Event;

public interface EventRepository {

    void processEvent(Event event);

    void addSubscriber(String host);

    void removeSubscriber(String host);

}
