package readside.application;

import readside.application.api.RoomService;
import readside.application.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Override
    public List<RoomDTO> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {
        return null;
    }
}
