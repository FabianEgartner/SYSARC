package eventside.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class BookingCancelledEvent extends Event {

    // todo: attributes

    public BookingCancelledEvent() {

        // todo: constructor

        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        this.uri = "/bookingCancelled/";
        this.className = "BookingCancelledEvent.class";
    }
}
