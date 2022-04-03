package eventside.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class BookingCancelledEvent extends Event {

    private final BookingId bookingId;

    @JsonCreator
    public BookingCancelledEvent(BookingId bookingId) {

        this.bookingId = bookingId;

        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        this.uri = "/bookingCancelled/";
        this.className = "BookingCancelledEvent.class";
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return "BookingCancelledEvent{bookingId=" + bookingId.getBookingId() + "}";
    }
}
