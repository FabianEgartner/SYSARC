package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import readside.application.api.RoomService;
import readside.application.dto.RoomDTO;
import readside.domain.api.RoomRepository;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {
        List<Room> rooms = roomRepository.getFreeRooms(fromDate, toDate, numberOfGuests);
        return RoomDTO.fromRooms(rooms);
    }
}
