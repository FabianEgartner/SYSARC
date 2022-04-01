package readside.application.dto;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDTO {

    private BookingId bookingId;
    private String customer;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<String> rooms;

    public BookingDTO(BookingId bookingId, String customer, LocalDate fromDate, LocalDate toDate, List<String> rooms) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rooms = rooms;
    }

    public static List<BookingDTO> fromBookings(List<Booking> bookings) {

        return bookings
                .stream()
                .map(booking ->
                        new BookingDTO(booking.getBookingId(),
                                booking.getCustomer(),
                                booking.getFromDate(),
                                booking.getToDate(),
                                booking.getRooms()))
                .collect(Collectors.toList());
    }
}
