package readside.application.api;

import org.springframework.stereotype.Component;
import readside.application.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public interface RoomServiceRead {

    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests);
}
