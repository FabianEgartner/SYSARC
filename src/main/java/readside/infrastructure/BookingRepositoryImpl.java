package readside.infrastructure;

import readside.domain.api.BookingRepository;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public List<Booking> getBookings(LocalDate fromDate, LocalDate toDate) {
        return null;
    }
}
