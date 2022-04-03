package writeside.view;

import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import readside.application.BookingServiceReadImpl;
import readside.application.RoomServiceReadImpl;
import readside.application.api.RoomServiceRead;
import readside.domain.NotEnoughRoomsException;
import writeside.domain.api.EventPublisher;
import writeside.application.api.BookingServiceWrite;
import writeside.domain.valueobjects.BookingId;
import writeside.infrastructure.EventPublisherImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingServiceWrite bookingServiceWrite;

    private final RoomServiceRead roomServiceRead = new RoomServiceReadImpl();

    private final BookingServiceReadImpl bookingServiceRead = new BookingServiceReadImpl();

    private final EventPublisher eventPublisher = new EventPublisherImpl();


    @GetMapping("/")
    public ModelAndView startPage() {
        return new ModelAndView("index");
    }

    @PostMapping("/bookRoom")
    public RedirectView submitBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests,
            RedirectAttributes redirectAttributes) {

        try {

            List<String> freeRooms = roomServiceRead.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));
            bookingServiceWrite.bookRoom(customerName, freeRooms, LocalDate.parse(fromDate), LocalDate.parse(toDate));

            eventPublisher.publishEvent(new BookingCreatedEvent(
                    new BookingId(),
                    customerName,
                    LocalDate.parse(fromDate),
                    LocalDate.parse(toDate),
                    freeRooms
            ));

        } catch (NotEnoughRoomsException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("bookingCreated", "failure");
            return new RedirectView("/");
        }

        redirectAttributes.addFlashAttribute("bookingCreated", "success");
        return new RedirectView("/");
    }
}
