package writeside.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import readside.application.BookingServiceReadImpl;
import readside.application.RoomServiceReadImpl;
import readside.application.api.BookingServiceRead;
import readside.application.api.RoomServiceRead;
import readside.domain.NotEnoughRoomsException;
import writeside.application.api.BookingServiceWrite;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingServiceWrite bookingServiceWrite;

    private final RoomServiceRead roomServiceRead = new RoomServiceReadImpl();

    private BookingServiceReadImpl bookingServiceRead = new BookingServiceReadImpl();

    @GetMapping("/")
    public ModelAndView startPage(Model model) {
        System.out.println("Test");
        return new ModelAndView("index.html");
    }

    @PostMapping("/bookRoom")
    public ModelAndView submitBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests) {

        System.out.println(numberOfGuests);

        try {
            List<String> freeRooms = roomServiceRead.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));
            System.out.println("Rooms: " + freeRooms);
            bookingServiceWrite.bookRoom(customerName, freeRooms, LocalDate.parse(fromDate), LocalDate.parse(toDate));

        } catch (NotEnoughRoomsException e) {
            e.printStackTrace();
        }

        bookingServiceRead.getBookings(LocalDate.parse("2022-04-02"), LocalDate.parse("2022-04-20"));

        return new ModelAndView("index.html");
    }
}
