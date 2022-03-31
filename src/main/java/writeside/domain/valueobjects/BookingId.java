package writeside.domain.valueobjects;

import java.util.UUID;

public class BookingId {

    private final UUID bookingId;

    public BookingId() {this.bookingId = UUID.randomUUID(); }
}
