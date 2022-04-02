package writeside.application.api;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceWrite {

    void bookRoom(String customer, List<String> bookedRooms, LocalDate fromDate, LocalDate toDate);
    boolean cancelBooking(BookingId id);
}
