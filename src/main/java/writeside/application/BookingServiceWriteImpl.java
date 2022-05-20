package writeside.application;

import org.springframework.stereotype.Component;
import readside.application.dto.BookingDTO;
import writeside.application.api.BookingServiceWrite;
import writeside.domain.Booking;
import writeside.domain.api.BookingRepositoryWrite;
import writeside.domain.valueobjects.BookingId;
import writeside.infrastructure.BookingRepositoryWriteImpl;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingServiceWriteImpl implements BookingServiceWrite {

    private final BookingRepositoryWrite bookingRepository = BookingRepositoryWriteImpl.getInstance();

    @Override
    public BookingDTO createBooking(String customer, List<String> bookedRooms, LocalDate fromDate, LocalDate toDate) {

        Booking booking = new Booking(new BookingId(), customer, bookedRooms, fromDate, toDate);
        bookingRepository.createBooking(booking);

        return BookingDTO.fromBooking(booking);
    }

    @Override
    public boolean cancelBooking(BookingId bookingId) {
        return bookingRepository.cancelBooking(bookingId);
    }
}
