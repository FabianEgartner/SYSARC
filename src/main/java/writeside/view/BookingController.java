package writeside.view;

import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import readside.application.BookingServiceReadImpl;
import readside.application.RoomServiceReadImpl;
import readside.application.api.BookingServiceRead;
import readside.application.api.RoomServiceRead;
import readside.application.dto.BookingDTO;
import readside.domain.NotEnoughRoomsException;
import writeside.EventPublisher;
import writeside.application.api.BookingServiceWrite;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingServiceWrite bookingServiceWrite;

    private final RoomServiceRead roomServiceRead = new RoomServiceReadImpl();

    private final BookingServiceReadImpl bookingServiceRead = new BookingServiceReadImpl();

    @Autowired
    private EventPublisher eventPublisher;

    @GetMapping("/")
    public ModelAndView startPage() {
        return new ModelAndView("index.html");
    }

    @PostMapping("/bookRoom")
    public ModelAndView submitBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests) {

        try {

            if ("".equals(customerName) || "".equals(fromDate) || "".equals(toDate) || "".equals(numberOfGuests))
            {
                throw new IllegalArgumentException("input is not valid");
            }

            List<String> freeRooms = roomServiceRead.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));
            BookingDTO createdBooking = bookingServiceWrite.bookRoom(customerName, freeRooms, LocalDate.parse(fromDate), LocalDate.parse(toDate));

            eventPublisher.publishEvent(new BookingCreatedEvent(
                    createdBooking.getBookingId(),
                    createdBooking.getCustomer(),
                    createdBooking.getFromDate(),
                    createdBooking.getToDate(),
                    createdBooking.getRooms()
            ));

        } catch (NotEnoughRoomsException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("redirect:"+"bookingFailed.html");
        }

        return new ModelAndView("redirect:"+"/");
    }
}
