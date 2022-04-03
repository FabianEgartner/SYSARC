package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readside.application.dto.BookingDTO;
import readside.domain.api.BookingRepositoryRead;
import readside.domain.api.RoomRepositoryRead;
import readside.infrastructure.BookingRepositoryReadImpl;
import readside.infrastructure.RoomRepositoryReadImpl;
import readside.domain.api.Projection;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private Projection projection;

    private BookingRepositoryRead bookingRepositoryRead = BookingRepositoryReadImpl.getInstance();

    private RoomRepositoryRead roomRepositoryRead = RoomRepositoryReadImpl.getInstance();

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
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepositoryRead.getAllBookings();

        return BookingDTO.fromBookings(bookings);
    }

    @GetMapping(value = "/allBookingsByPeriod")
    public List<BookingDTO> getAllBookingsByPeriod(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate) {

        List<Booking> bookings = bookingRepositoryRead.getBookingsByPeriod(LocalDate.parse(fromDate), LocalDate.parse(toDate));

        return BookingDTO.fromBookings(bookings);
    }

    @GetMapping(value = "/freeRooms")
    public List<String> getFreeRooms(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests) {

        return roomRepositoryRead.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));
    }

}