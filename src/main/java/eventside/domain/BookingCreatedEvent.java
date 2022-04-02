package eventside.domain;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class BookingCreatedEvent extends Event {

    private final BookingId bookingId;
    private final String customer;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final List<String> rooms;

    public BookingCreatedEvent(BookingId bookingId, String customer, LocalDate fromDate, LocalDate toDate, List<String> rooms) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rooms = rooms;

        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
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
