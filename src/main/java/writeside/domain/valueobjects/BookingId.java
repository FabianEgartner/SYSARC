package writeside.domain.valueobjects;

import java.util.UUID;

public class BookingId {

    private final UUID bookingId;

    public BookingId() {
        this.bookingId = UUID.randomUUID();
    }

    public BookingId(String id) {
        this.bookingId = UUID.fromString(id);
    }

    public UUID getBookingId() {
        return bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId bookingId2 = (BookingId) o;
        return this.bookingId.equals(bookingId2.getBookingId());
    }
}
