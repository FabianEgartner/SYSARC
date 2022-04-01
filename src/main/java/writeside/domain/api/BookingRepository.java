package writeside.domain.api;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

import java.util.Optional;

public interface BookingRepository {

    void createBooking(Booking booking);
    void cancelBooking(BookingId id);
    Optional<Booking> getBooking(BookingId bookingId);
}
