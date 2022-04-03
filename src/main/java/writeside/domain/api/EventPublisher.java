package writeside.domain.api;

import eventside.domain.Event;
import org.springframework.stereotype.Component;

@Component
public interface EventPublisher {

    Boolean publishEvent(Event event);

}
