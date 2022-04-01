package readside.application.api;

import readside.application.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDTO> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
