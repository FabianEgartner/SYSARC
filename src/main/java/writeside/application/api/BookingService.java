package writeside.application.api;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public interface BookingService {

    void bookRoom(String customer, String roomNumber, LocalDate fromDate, LocalDate toDate);
    void cancelBooking(BookingId id);
}
