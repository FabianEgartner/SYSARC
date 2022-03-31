package writeside.application;

import org.springframework.beans.factory.annotation.Autowired;
import writeside.application.api.BookingService;
import writeside.domain.Booking;
import writeside.domain.Room;
import writeside.domain.api.BookingRepository;
import writeside.domain.api.RoomRepository;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public boolean bookRoom(String customer, List<String> roomNumbers, LocalDate fromDate, LocalDate toDate) {

        try
        {
            for (String roomNumber : roomNumbers)
            {
                Optional<Room> optRoom = roomRepository.getRoom(roomNumber);

                if (optRoom.isEmpty())
                    throw new IllegalArgumentException("room does not exist or is already occupied");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        Booking booking = new Booking(new BookingId(), customer, roomNumbers, fromDate, toDate);
        bookingRepository.createBooking(booking);
        return true;
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
