package readside.application.api;

import readside.application.dto.BookingDTO;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate);
}
