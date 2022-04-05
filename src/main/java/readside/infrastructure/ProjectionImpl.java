package readside.infrastructure;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.stereotype.Component;
import readside.domain.Room;
import readside.domain.OccupiedPeriod;
import readside.domain.api.BookingRepositoryRead;
import readside.domain.api.RoomRepositoryRead;
import readside.domain.api.Projection;
import writeside.domain.Booking;

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

            for (int i = 0; i < bookings.size(); i++) {

                if (bookings.get(i).getBookingId().equals(bookingCancelledEvent.getBookingId())) {
                    bookings.remove(i);
                }
            }
        }

    }
}
