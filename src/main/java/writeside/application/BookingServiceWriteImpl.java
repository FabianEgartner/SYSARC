package writeside.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.application.dto.BookingDTO;
import readside.infrastructure.RoomRepositoryReadImpl;
import writeside.application.api.BookingServiceWrite;
import writeside.domain.Booking;
import writeside.domain.api.BookingRepositoryWrite;
import readside.domain.api.RoomRepositoryRead;
import writeside.domain.valueobjects.BookingId;
import writeside.infrastructure.BookingRepositoryWriteImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class BookingServiceWriteImpl implements BookingServiceWrite {

    private BookingRepositoryWrite bookingRepository = BookingRepositoryWriteImpl.getInstance();

    @Override
    public BookingDTO bookRoom(String customer, List<String> bookedRooms, LocalDate fromDate, LocalDate toDate) {

        Booking booking = new Booking(new BookingId(), customer, bookedRooms, fromDate, toDate);
        bookingRepository.createBooking(booking);

        return BookingDTO.fromBooking(booking);
    }

    @Override
    public boolean cancelBooking(BookingId bookingId) {

        try
        {
            Optional<Booking> optBooking = bookingRepository.getBooking(bookingId);

            if (optBooking.isEmpty())
                throw new IllegalArgumentException("booking does not exist");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        bookingRepository.cancelBooking(bookingId);
        return true;
    }
}
