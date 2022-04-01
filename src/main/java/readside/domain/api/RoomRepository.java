package readside.domain.api;

import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    List<Room> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
