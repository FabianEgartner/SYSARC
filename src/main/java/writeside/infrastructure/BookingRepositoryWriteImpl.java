package writeside.infrastructure;

import org.springframework.stereotype.Component;
import readside.infrastructure.BookingRepositoryReadImpl;
import readside.infrastructure.RoomRepositoryReadImpl;
import writeside.domain.Booking;
import writeside.domain.api.BookingRepositoryWrite;
import writeside.domain.valueobjects.BookingId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookingRepositoryWriteImpl implements BookingRepositoryWrite {

    private final List<Booking> bookings = new ArrayList<>();
    private static BookingRepositoryWriteImpl instance;

    public static BookingRepositoryWriteImpl getInstance()
    {
        if (null == BookingRepositoryWriteImpl.instance) {
            new BookingRepositoryWriteImpl();
        }

        return BookingRepositoryWriteImpl.instance;
    }

    private BookingRepositoryWriteImpl() {
        BookingRepositoryWriteImpl.instance = this;
    }

    @Override
    public void createBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void cancelBooking(BookingId bookingId) {
        for (int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).getBookingId().equals(bookingId))
            {
                bookings.remove(i);
                return;
            }
        }
    }

    @Override
    public Optional<Booking> getBooking(BookingId bookingId) {

        for (Booking booking : bookings)
        {
            if (booking.getBookingId().equals(bookingId))
                return Optional.of(booking);
        }

        return Optional.empty();
    }
}
