package readside.domain.api;

import eventside.domain.Event;

public interface Projection {

    void processEvent(Event event);

}
