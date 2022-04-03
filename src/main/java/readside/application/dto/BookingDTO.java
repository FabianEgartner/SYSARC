package readside.application.dto;

import writeside.domain.Booking;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDTO {

    private final BookingId bookingId;
    private final String customer;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final List<String> rooms;

    public BookingDTO(BookingId bookingId, String customer, LocalDate fromDate, LocalDate toDate, List<String> rooms) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rooms = rooms;
    }

    public static BookingDTO fromBooking(Booking booking) {

        return new BookingDTO(
                        booking.getBookingId(),
                        booking.getCustomer(),
                        booking.getFromDate(),
                        booking.getToDate(),
                        booking.getRooms());
    }

    public static List<BookingDTO> fromBookings(List<Booking> bookings) {

        return bookings
                .stream()
                .map(booking -> new BookingDTO(
                        booking.getBookingId(),
                        booking.getCustomer(),
                        booking.getFromDate(),
                        booking.getToDate(),
                        booking.getRooms()))
                .collect(Collectors.toList());
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public List<String> getRooms() {
        return rooms;
    }
}
