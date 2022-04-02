package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.application.api.BookingServiceRead;
import readside.application.dto.BookingDTO;
import readside.domain.api.BookingRepositoryRead;
import readside.infrastructure.BookingRepositoryReadImpl;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingServiceReadImpl implements BookingServiceRead {

    private final BookingRepositoryRead bookingRepositoryRead = new BookingRepositoryReadImpl();

    @Override
    public List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate) {

        List<Booking> bookings = bookingRepositoryRead.getBookings(fromDate, toDate);
        return BookingDTO.fromBookings(bookings);
    }
}
