package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.application.ReadSideEventPublisher;
import readside.application.dto.BookingDTO;
import readside.domain.api.BookingRepositoryRead;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepositoryReadImpl implements BookingRepositoryRead {

    private final List<Booking> bookings = new ArrayList<>();

    private ReadSideEventPublisher readSideEventPublisher = new ReadSideEventPublisher();

    public BookingRepositoryReadImpl()
    {
//        try {
//            readSideEventPublisher.publishEvent(new SubscriptionEvent(event -> System.out.println("BOOKING_REPO_READ_INFORMED")));
//        } catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public List<Booking> getBookings(LocalDate fromDate, LocalDate toDate) {
        List<Booking> bookingsInPeriod = new ArrayList<>();

        for (Booking booking : bookings)
        {
            if (booking.getFromDate().isAfter(fromDate.minusDays(1)) && booking.getToDate().isBefore(toDate.plusDays(1)))
                bookingsInPeriod.add(booking);
        }

        return bookingsInPeriod;
    }

    @Override
    public void addBooking(Booking booking) {

    }
}
