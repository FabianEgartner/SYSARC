package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readside.application.api.BookingServiceRead;
import readside.application.api.RoomServiceRead;
import readside.domain.api.BookingRepositoryRead;
import readside.infrastructure.BookingRepositoryReadImpl;
import readside.rest.api.Projection;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    Projection projection;

    BookingRepositoryRead bookingRepositoryRead = BookingRepositoryReadImpl.getInstance();

    @PostMapping(value = "/bookingCreated", consumes = "application/json", produces = "application/json")
    public boolean bookingCreated(@RequestBody BookingCreatedEvent event) {
        System.out.println("[ReadSide] Event received: " + event);
        projection.processEvent(event);

        return true;
    }

    @PostMapping(value = "/bookingCancelled", consumes = "application/json", produces = "application/json")
    public boolean bookingCancelled(@RequestBody BookingCancelledEvent event) {
        System.out.println("[ReadSide] Event received: " + event);
        projection.processEvent(event);

        return true;
    }

    @GetMapping(value = "/allBookings")
    public List<Booking> getAllBookings() {
        return bookingRepositoryRead.getAllBookings();
    }

    // @PostMapping(value = "/allBookings", consumes = "application/json", produces = "application/json")
    @GetMapping(value = "/allBookingsByPeriod")
    public List<Booking> getAllBookings(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        return bookingRepositoryRead.getBookingsByPeriod(LocalDate.parse(fromDate), LocalDate.parse(toDate));
    }
}
