package writeside.infrastructure;

import eventside.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import writeside.domain.api.EventPublisher;

@Component
public class EventPublisherImpl implements EventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");

    @Override
    public Boolean publishEvent(Event event) {
        System.out.println("[WriteSide] Event published: " + event);
        return localApiClient
                .post()
                .uri(event.getUri())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), event.getClass())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
