package readside.domain.api;

import org.springframework.stereotype.Component;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;

@Component
public interface RoomRepositoryRead {

    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
