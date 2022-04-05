package readside.domain.api;

import readside.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepositoryRead {

    List<Room> getRooms();
    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
