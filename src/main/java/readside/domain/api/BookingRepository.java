package readside.domain.api;

import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    List<Booking> getBookings(LocalDate fromDate, LocalDate toDate);
}
