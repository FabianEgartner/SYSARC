package writeside.view;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import readside.application.dto.BookingDTO;
import writeside.domain.api.EventPublisher;
import writeside.application.api.BookingServiceWrite;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private BookingServiceWrite bookingServiceWrite;

    private final WebClient webClient = WebClient.create("http://localhost:8082");

    @GetMapping("/")
    public ModelAndView startPage() {
        return new ModelAndView("index");
    }

    @PostMapping("/createBooking")
    public RedirectView createBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests,
            RedirectAttributes redirectAttributes) {

        List rooms = webClient.get()
                .uri("/freeRooms/?fromDate=" + fromDate + "&toDate=" + toDate + "&numberOfGuests=" + numberOfGuests)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        if (rooms == null || rooms.isEmpty()) {
            redirectAttributes.addFlashAttribute("bookingCreated", "failure");
            return new RedirectView("/");
        }

        BookingDTO bookingDTO = bookingServiceWrite.createBooking(customerName, rooms, LocalDate.parse(fromDate), LocalDate.parse(toDate));

        eventPublisher.publishEvent(new BookingCreatedEvent(
                bookingDTO.getBookingId(),
                bookingDTO.getCustomer(),
                bookingDTO.getFromDate(),
                bookingDTO.getToDate(),
                bookingDTO.getRooms()
        ));

        redirectAttributes.addFlashAttribute("bookingCreated", "success");
        return new RedirectView("/");
    }

    @PostMapping("/cancelBooking")
    public RedirectView cancelBooking(
            @RequestParam("bookingId") String bookingId,
            RedirectAttributes redirectAttributes) {

        if ("".equals(bookingId) || bookingId.length() != 36) {
            redirectAttributes.addFlashAttribute("bookingCancelled", "invalid");
            return new RedirectView("bookingOverview");
        }

        BookingId id = new BookingId(bookingId);

        if (!bookingServiceWrite.cancelBooking(id)) {
            redirectAttributes.addFlashAttribute("bookingCancelled", "failure");
            return new RedirectView("bookingOverview");
        }

        eventPublisher.publishEvent(new BookingCancelledEvent(id));

        redirectAttributes.addFlashAttribute("bookingCancelled", "success");
        return new RedirectView("bookingOverview");
    }

    @GetMapping("/bookingOverview")
    public ModelAndView bookingOverview(Model model) {
        return new ModelAndView("bookingOverview");
    }

    @PostMapping("/allBookings")
    public RedirectView getAllBookings(RedirectAttributes redirectAttributes) {

        List bookings = webClient.get()
                .uri("/allBookings/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        redirectAttributes.addFlashAttribute("bookings", bookings);

        return new RedirectView("bookingOverview");
    }

    @PostMapping("/allBookingsByPeriod")
    public RedirectView getAllBookingsByPeriod(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            RedirectAttributes redirectAttributes) {

        List bookings = webClient.get()
                .uri("/allBookingsByPeriod/?fromDate=" + fromDate + "&toDate=" + toDate)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        redirectAttributes.addFlashAttribute("bookings", bookings);

        return new RedirectView("bookingOverview");
    }

    @GetMapping("/roomOverview")
    public ModelAndView roomOverview(Model model) {
        return new ModelAndView("roomOverview");
    }

    @PostMapping("/freeRooms")
    public RedirectView getFreeRooms(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("numberOfGuests") String numberOfGuests,
            RedirectAttributes redirectAttributes) {

        List rooms = webClient.get()
                .uri("/freeRooms/?fromDate=" + fromDate + "&toDate=" + toDate + "&numberOfGuests=" + numberOfGuests)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        redirectAttributes.addFlashAttribute("rooms", rooms);

        return new RedirectView("roomOverview");
    }

}
