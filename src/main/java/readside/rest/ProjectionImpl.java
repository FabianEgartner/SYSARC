package readside.rest;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readside.domain.api.BookingRepositoryRead;
import readside.rest.api.Projection;
import writeside.domain.Booking;

@Component
public class ProjectionImpl implements Projection {

    @Autowired
    BookingRepositoryRead bookingRepositoryRead;

    @Override
    public void processEvent(Event event) {

        if (event instanceof BookingCreatedEvent) {

            BookingCreatedEvent bookingCreatedEvent = (BookingCreatedEvent) event;

            bookingRepositoryRead.addBooking(new Booking(
                    bookingCreatedEvent.getBookingId(),
                    bookingCreatedEvent.getCustomer(),
                    bookingCreatedEvent.getRooms(),
                    bookingCreatedEvent.getFromDate(),
                    bookingCreatedEvent.getToDate()
            ));

        } else if (event instanceof BookingCancelledEvent) {

            BookingCancelledEvent bookingCancelledEvent = (BookingCancelledEvent) event;

            // TODO

        }

    }
}
