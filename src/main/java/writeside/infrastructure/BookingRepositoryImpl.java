package writeside.infrastructure;

import writeside.domain.Booking;
import writeside.domain.api.BookingRepository;
import writeside.domain.valueobjects.BookingId;

import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

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
}
