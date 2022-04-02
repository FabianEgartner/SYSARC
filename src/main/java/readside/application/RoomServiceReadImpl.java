package readside.application;

import org.springframework.beans.factory.annotation.Autowired;
import readside.application.api.RoomServiceRead;
import readside.application.dto.RoomDTO;
import readside.domain.api.RoomRepositoryRead;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.List;

public class RoomServiceReadImpl implements RoomServiceRead {

    @Autowired
    private RoomRepositoryRead roomRepositoryRead;

    @Override
    public List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {
        List<String> rooms = roomRepositoryRead.getFreeRooms(fromDate, toDate, numberOfGuests);
        return rooms;
    }
}