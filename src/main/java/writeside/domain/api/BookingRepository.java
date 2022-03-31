package writeside.domain.api;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public interface BookingRepository {

    void createBooking(Booking booking);
    void cancelBooking(BookingId id);
}
