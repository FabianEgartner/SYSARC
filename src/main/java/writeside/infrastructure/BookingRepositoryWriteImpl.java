package writeside.infrastructure;

import org.springframework.stereotype.Component;
import writeside.domain.Booking;
import writeside.domain.api.BookingRepositoryWrite;
import writeside.domain.valueobjects.BookingId;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepositoryWriteImpl implements BookingRepositoryWrite {

    private static final List<Booking> bookings = new ArrayList<>();
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
    public boolean cancelBooking(BookingId bookingId) {

        for (int i = 0; i < BookingRepositoryWriteImpl.bookings.size(); i++)
        {
            if (BookingRepositoryWriteImpl.bookings.get(i).getBookingId().equals(bookingId))
            {
                BookingRepositoryWriteImpl.bookings.remove(i);
                return true;
            }
        }

        return false;
    }
}
