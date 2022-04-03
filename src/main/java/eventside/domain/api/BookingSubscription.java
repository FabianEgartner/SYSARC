package eventside.domain.api;

import eventside.domain.Event;

public interface BookingSubscription {

    void notify(Event event);
}
