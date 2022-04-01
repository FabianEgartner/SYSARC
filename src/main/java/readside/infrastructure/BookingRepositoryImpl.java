package readside.infrastructure;

import readside.domain.api.BookingRepository;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getBookings(LocalDate fromDate, LocalDate toDate) {
        for (Booking booking : bookings)
        {
            if (booking.getFromDate(). && booking.getToDate() <= toDate)
            {

            }
        }
    }
}
