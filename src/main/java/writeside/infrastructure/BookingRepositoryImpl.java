package writeside.infrastructure;

import org.springframework.stereotype.Component;
import writeside.domain.Booking;
import writeside.domain.Room;
import writeside.domain.RoomStatus;
import writeside.domain.api.BookingRepository;
import writeside.domain.valueobjects.BookingId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
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
