package writeside.domain.api;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

import java.util.Optional;

public interface BookingRepositoryWrite {

    void createBooking(Booking booking);

    boolean cancelBooking(BookingId id);

    Optional<Booking> getBooking(BookingId bookingId);

}
