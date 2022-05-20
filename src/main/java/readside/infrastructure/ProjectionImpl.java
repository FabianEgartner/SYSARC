package readside.infrastructure;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.stereotype.Component;
import writeside.domain.Room;
import writeside.domain.OccupiedPeriod;
import readside.domain.api.BookingRepositoryRead;
import readside.domain.api.RoomRepositoryRead;
import readside.domain.api.Projection;
import writeside.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectionImpl implements Projection {

    BookingRepositoryRead bookingRepositoryRead = BookingRepositoryReadImpl.getInstance();
    RoomRepositoryRead roomRepositoryRead = RoomRepositoryReadImpl.getInstance();

    @Override
    public void processEvent(Event event) {

        if (event instanceof BookingCreatedEvent) {

            BookingCreatedEvent bookingCreatedEvent = (BookingCreatedEvent) event;

            List<Booking> bookings = bookingRepositoryRead.getAllBookings();

            bookings.add(new Booking(
                    bookingCreatedEvent.getBookingId(),
                    bookingCreatedEvent.getCustomer(),
                    bookingCreatedEvent.getRooms(),
                    bookingCreatedEvent.getFromDate(),
                    bookingCreatedEvent.getToDate()
            ));


            List<Room> rooms = roomRepositoryRead.getRooms();

            for (Room room : rooms)
            {
                if (bookingCreatedEvent.getRooms().contains(room.getRoomNumber()))
                {
                    room.addOccupiedPeriod(new OccupiedPeriod(
                            bookingCreatedEvent.getFromDate(),
                            bookingCreatedEvent.getToDate()
                    ));
                }
            }

        } else if (event instanceof BookingCancelledEvent) {

            BookingCancelledEvent bookingCancelledEvent = (BookingCancelledEvent) event;
            List<Booking> bookings = bookingRepositoryRead.getAllBookings();

            List<Room> allRooms = roomRepositoryRead.getRooms();
            LocalDate fromDateToRemove = null;
            LocalDate toDateToRemove = null;
            List<String> roomsToRemove = null;

            for (int i = 0; i < bookings.size(); i++) {

                Booking currentBooking = bookings.get(i);

                if (currentBooking.getBookingId().equals(bookingCancelledEvent.getBookingId())) {
                    fromDateToRemove = currentBooking.getFromDate();
                    toDateToRemove = currentBooking.getToDate();
                    roomsToRemove = currentBooking.getRooms();
                    bookings.remove(i);
                }
            }

            for (Room room : allRooms)
            {
                for (String roomToRemove : roomsToRemove)
                {
                    if (room.getRoomNumber().equals(roomToRemove))
                    {
                        List<OccupiedPeriod> occupiedPeriods = room.getOccupiedPeriods();

                        for (int i = 0; i < occupiedPeriods.size(); i++)
                        {
                            if (occupiedPeriods.get(i).getFromDate().equals(fromDateToRemove) && occupiedPeriods.get(i).getToDate().equals(toDateToRemove))
                            {
                                occupiedPeriods.remove(i);
                            }
                        }
                    }
                }
            }
        }
    }
}
