package eventside.infrastructure;

import eventside.domain.Event;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Subscriber {

    private final String host;
    private final WebClient webClient;

    public Subscriber(String host) {
        this.host = host;
        this.webClient = WebClient.create(host);
    }

    public String getHost() {
        return host;
    }

    public void notify(Event event) {
        webClient.post()
                .uri(event.getUri())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
