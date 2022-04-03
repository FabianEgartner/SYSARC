package writeside.domain.api;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public interface EventPublisher {

    Boolean publishEvent(BookingCreatedEvent event);

    Boolean publishEvent(BookingCancelledEvent event);

}
