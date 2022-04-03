package eventside.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public BookingCreatedEvent(BookingId bookingId, String customer, LocalDate fromDate, LocalDate toDate, List<String> rooms) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rooms = rooms;

        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        this.uri = "/bookingCreated/";
        this.className = "BookingCreatedEvent.class";
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

    @Override
    public String toString() {
        return "BookingCreatedEvent{" +
                "bookingId=" + bookingId.getBookingId() +
                ", customer='" + customer + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", rooms=" + rooms +
                '}';
    }
}
