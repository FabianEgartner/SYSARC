package writeside.application.api;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public interface BookingService {

    void bookRoom(LocalDate from, LocalDate until, int roomNr, String name);

    void cancelBooking(BookingId id);

}
