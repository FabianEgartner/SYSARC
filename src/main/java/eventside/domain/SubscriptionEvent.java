package eventside.domain;

import eventside.domain.api.BookingSubscription;
import writeside.domain.valueobjects.BookingId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class SubscriptionEvent extends Event {

    private BookingSubscription subscriber;

    public SubscriptionEvent(){}

    public SubscriptionEvent(BookingSubscription subscriber) {
        this.subscriber = subscriber;

        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        this.uri = "/subscribed/";
        this.className = "SubscriptionEvent.class";
    }
}
