package readside.domain.api;

import readside.domain.AvailableRoom;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepositoryRead {

    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
    List<AvailableRoom> getAvailableRooms();
}
