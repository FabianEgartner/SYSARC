package readside.application.api;

import readside.application.dto.RoomDTO;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    public List<RoomDTO> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
