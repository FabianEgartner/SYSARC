package readside.domain.api;

import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepositoryRead {

    List<Booking> getAllBookings();
    List<Booking> getBookingsByPeriod(LocalDate fromDate, LocalDate toDate);
}
