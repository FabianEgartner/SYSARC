package writeside.application.api;

import readside.application.dto.BookingDTO;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceWrite {

    BookingDTO createBooking(String customer, List<String> bookedRooms, LocalDate fromDate, LocalDate toDate);

    boolean cancelBooking(BookingId id);
}
