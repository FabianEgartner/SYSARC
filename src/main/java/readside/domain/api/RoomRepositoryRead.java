package readside.domain.api;

import org.springframework.stereotype.Component;
import readside.domain.AvailableRoom;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepositoryRead {

    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
    List<AvailableRoom> getAvailableRooms();
}
