package readside.application.api;

import org.springframework.stereotype.Component;
import readside.application.dto.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceRead {

    List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate);
}
