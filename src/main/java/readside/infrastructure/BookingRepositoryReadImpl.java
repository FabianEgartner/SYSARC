package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.domain.AvailableRoom;
import readside.domain.api.BookingRepositoryRead;
import writeside.domain.Booking;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepositoryReadImpl implements BookingRepositoryRead {

    private final List<Booking> bookings = new ArrayList<>();
    private static BookingRepositoryReadImpl instance;

    public static BookingRepositoryReadImpl getInstance()
    {
        if (null == BookingRepositoryReadImpl.instance) {
            new BookingRepositoryReadImpl();
        }

        return BookingRepositoryReadImpl.instance;
    }

    private BookingRepositoryReadImpl() {
        BookingRepositoryReadImpl.instance = this;
    }

    @Override
    public List<Booking> getBookingsByPeriod(LocalDate fromDate, LocalDate toDate) {
        List<Booking> bookingsInPeriod = new ArrayList<>();

        for (Booking booking : bookings)
        {
            if (booking.getFromDate().isAfter(fromDate.minusDays(1)) && booking.getToDate().isBefore(toDate.plusDays(1)))
                bookingsInPeriod.add(booking);
        }

        return bookingsInPeriod;
    }

    @Override
    public List<Booking> getAllBookings() {
        return this.bookings;
    }
}
