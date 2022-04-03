package readside.domain.api;

import org.springframework.stereotype.Component;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepositoryRead {

    List<Booking> getBookings(LocalDate fromDate, LocalDate toDate);
}
