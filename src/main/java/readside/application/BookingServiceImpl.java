package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import readside.application.api.BookingService;
import readside.application.dto.BookingDTO;
import writeside.domain.Booking;
import readside.domain.api.BookingRepository;
import writeside.domain.api.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate) {

        List<Booking> bookings = bookingRepository.getBookings(fromDate, toDate);
        return BookingDTO.fromBookings(bookings);
    }
}
