package writeside.application;

import writeside.application.api.BookingService;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public class BookingServiceImpl implements BookingService {

    @Override
    public void bookRoom(LocalDate from, LocalDate until, int roomNr, String name) {

    }

    @Override
    public void cancelBooking(BookingId id) {

    }

}
