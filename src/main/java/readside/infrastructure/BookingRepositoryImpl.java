package readside.infrastructure;

import readside.domain.AvailableRoom;
import readside.domain.api.BookingRepository;
import writeside.domain.Booking;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getBookings(LocalDate fromDate, LocalDate toDate) {
        List<Booking> bookingsInPeriod = new ArrayList<>();

        for (Booking booking : bookings)
        {
            if(booking.getFromDate().isAfter(fromDate.minusDays(1)) && booking.getToDate().isBefore(toDate.plusDays(1)))
                bookingsInPeriod.add(booking);
        }

        return bookingsInPeriod;
    }
}
