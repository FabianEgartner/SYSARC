package writeside.view;

import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;
import readside.application.BookingServiceReadImpl;
import readside.application.RoomServiceReadImpl;
import readside.application.api.BookingServiceRead;
import readside.application.api.RoomServiceRead;
import readside.application.dto.BookingDTO;
import readside.domain.NotEnoughRoomsException;
import writeside.domain.Booking;
import writeside.domain.api.EventPublisher;
import writeside.application.api.BookingServiceWrite;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private BookingServiceWrite bookingServiceWrite;

    private final RoomServiceRead roomServiceRead = new RoomServiceReadImpl();

    private final BookingServiceRead bookingServiceRead = new BookingServiceReadImpl();


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
            BookingDTO bookingDTO = bookingServiceWrite.bookRoom(customerName, freeRooms, LocalDate.parse(fromDate), LocalDate.parse(toDate));

            eventPublisher.publishEvent(new BookingCreatedEvent(
                    bookingDTO.getBookingId(),
                    bookingDTO.getCustomer(),
                    bookingDTO.getFromDate(),
                    bookingDTO.getToDate(),
                    bookingDTO.getRooms()
            ));

        } catch (NotEnoughRoomsException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("bookingCreated", "failure");
            return new RedirectView("/");
        }

//        WebClient webClient = WebClient.create("http://localhost:8082");
//        List bookings = webClient.get()
//                .uri("/allBookings/")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(List.class)
//                .block();
//
//        System.out.println("BOOKINGS:" + bookings);

//        WebClient webClient2 = WebClient.create("http://localhost:8082");
//        List bookings = webClient2.get()
//                .uri("/allBookingsByPeriod/?fromDate=" + fromDate + "&toDate=" + toDate)
//                //.contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(List.class)
//                .block();
//
//                System.out.println("BOOKINGS:" + bookings);

        WebClient webClient2 = WebClient.create("http://localhost:8082");
        List bookings = webClient2.get()
                .uri("/allBookingsByPeriod/?fromDate=2022-01-01&toDate=2022-01-03")
                //.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        System.out.println("BOOKINGS:" + bookings);


//        String json = webClient.get()
//                .uri("/allBookings/")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

//        System.out.println(json);

        try {
            roomServiceRead.getFreeRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate), Integer.parseInt(numberOfGuests));
        } catch (NotEnoughRoomsException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("bookingCreated", "success");
        return new RedirectView("/");
    }

    @GetMapping("/bookingOverview")
    public ModelAndView bookingOverview() {



        return new ModelAndView("bookingOverview");
    }
}
