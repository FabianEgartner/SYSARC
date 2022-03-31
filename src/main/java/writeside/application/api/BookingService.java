package writeside.application.api;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    boolean bookRoom(String customer, List<String> roomNumber, LocalDate fromDate, LocalDate toDate);
    boolean cancelBooking(BookingId id);
}
