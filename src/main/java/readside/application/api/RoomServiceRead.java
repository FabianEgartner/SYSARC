package readside.application.api;

import readside.domain.NotEnoughRoomsException;

import java.time.LocalDate;
import java.util.List;

public interface RoomServiceRead {

    List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) throws NotEnoughRoomsException;
}
