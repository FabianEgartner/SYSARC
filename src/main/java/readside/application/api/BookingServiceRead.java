package readside.application.api;

import readside.application.dto.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceRead {

    List<BookingDTO> getBookings(LocalDate fromDate, LocalDate toDate);

    void addBooking(String customer, List<String> bookedRooms, LocalDate fromDate, LocalDate toDate);

}
