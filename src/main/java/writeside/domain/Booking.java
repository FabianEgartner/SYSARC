package writeside.domain;

import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private BookingId bookingId;
    private String customer;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<String> rooms = new ArrayList<>();


    public Booking(BookingId bookingId, String customer, String roomNumber, LocalDate fromDate, LocalDate toDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rooms.add(roomNumber);
    }


    public BookingId getBookingId() {
        return bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customer='" + customer + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
