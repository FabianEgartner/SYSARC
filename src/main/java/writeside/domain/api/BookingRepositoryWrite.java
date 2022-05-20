package writeside.domain.api;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

public interface BookingRepositoryWrite {

    void createBooking(Booking booking);

    boolean cancelBooking(BookingId id);
}
