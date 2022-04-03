package writeside.infrastructure;

import eventside.domain.BookingCancelledEvent;
import eventside.domain.BookingCreatedEvent;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import writeside.domain.api.EventPublisher;

public class EventPublisherImpl implements EventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");

    @Override
    public Boolean publishEvent(BookingCreatedEvent event) {
        System.out.println("[WriteSide] Event published: " + event);
        return localApiClient
                .post()
                .uri("/bookingCreated/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), BookingCreatedEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @Override
    public Boolean publishEvent(BookingCancelledEvent event) {
        System.out.println("[WriteSide] Event published: " + event);
        return localApiClient
                .post()
                .uri("/bookingCancelled/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), BookingCancelledEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
