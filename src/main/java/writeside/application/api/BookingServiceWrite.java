package writeside.application.api;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public interface BookingServiceWrite {

    boolean bookRoom(String customer, int numberOfGuests, LocalDate fromDate, LocalDate toDate);
    boolean cancelBooking(BookingId id);
}
