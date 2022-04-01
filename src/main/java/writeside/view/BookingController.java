package writeside.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import readside.domain.api.RoomRepository;
import writeside.domain.Booking;
import writeside.domain.Room;
import writeside.domain.api.BookingRepository;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    // TODO: shouldn't this be services instead of repositories?
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/")
    public ModelAndView startPage(Model model) {
        return new ModelAndView("index.html");
    }

    @PostMapping("/bookRoom")
    public ModelAndView submitBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests) {

        List<Room> rooms = roomRepository.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));


        // TODO: handle situation where not enough rooms are available
        if (!rooms.isEmpty()) {
            bookingRepository.createBooking(new Booking(new BookingId(), customerName, LocalDate.parse(fromDate), LocalDate.parse(toDate)));
        }

        return new ModelAndView("index.html");
    }
}
