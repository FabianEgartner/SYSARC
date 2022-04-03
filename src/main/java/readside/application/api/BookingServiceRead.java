package readside.application.api;

import readside.application.dto.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceRead {

    List<BookingDTO> getBookingsByPeriod(LocalDate fromDate, LocalDate toDate);
    List<BookingDTO> getAllBookings();

}
