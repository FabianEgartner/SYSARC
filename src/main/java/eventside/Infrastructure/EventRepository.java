package eventside.Infrastructure;

import eventside.domain.BookingCreatedEvent;
import eventside.domain.Event;
import eventside.domain.api.BookingSubscription;
import org.springframework.stereotype.Component;
import readside.domain.api.BookingRepositoryRead;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();
    private List<BookingSubscription> bookingSubscribers = new ArrayList<>();

    public void processEvent(Event event) {
        events.add(event);

        for (BookingSubscription subscriber : bookingSubscribers)
        {
            subscriber.notify(event);
        }
    }

    public void addBookingListener(BookingSubscription subscriber)
    {
        bookingSubscribers.add(subscriber);

        for (Event event : events)
        {
            subscriber.notify(event);
        }
    }

    public void removeBookingListener(BookingSubscription bookingCreatedSub)
    {
        bookingSubscribers.remove(bookingCreatedSub);
    }
}
