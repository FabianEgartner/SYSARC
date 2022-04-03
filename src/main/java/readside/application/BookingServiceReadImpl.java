package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.application.api.BookingServiceRead;
import readside.application.dto.BookingDTO;
import readside.domain.api.BookingRepositoryRead;
import readside.infrastructure.BookingRepositoryReadImpl;
import writeside.domain.Booking;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Component
public class BookingServiceReadImpl implements BookingServiceRead {

    private BookingRepositoryRead bookingRepositoryRead = new BookingRepositoryReadImpl();

    @Override
    public List<BookingDTO> getBookingsByPeriod(LocalDate fromDate, LocalDate toDate) {
        List<Booking> bookings = bookingRepositoryRead.getBookingsByPeriod(fromDate, toDate);
        return BookingDTO.fromBookings(bookings);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepositoryRead.getAllBookings();
        return BookingDTO.fromBookings(bookings);
    }

}
