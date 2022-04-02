package readside.domain.api;

import org.springframework.stereotype.Component;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Component
public interface BookingRepositoryRead {

    void onWriteEvent();
    List<Booking> getBookings(LocalDate fromDate, LocalDate toDate);

}
