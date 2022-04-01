package readside.application;

import readside.application.api.BookingService;
import readside.application.dto.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    @Override
    public List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate) {
        return null;
    }
}
