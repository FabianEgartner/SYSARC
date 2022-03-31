package writeside.application;

import org.springframework.beans.factory.annotation.Autowired;
import writeside.application.api.BookingService;
import writeside.domain.Booking;
import writeside.domain.api.BookingRepository;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void bookRoom(String customer, String roomNumber, LocalDate fromDate, LocalDate toDate) {
        Booking booking = new Booking(new BookingId(), customer, roomNumber, fromDate, toDate);
        bookingRepository.createBooking(booking);
    }

    @Override
    public void cancelBooking(BookingId bookingId) {
        bookingRepository.cancelBooking(bookingId);
    }
}
