package readside.application.dto;

import writeside.domain.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDTO {

    private final String roomNumber;
    private final int numberOfBeds;

    public RoomDTO(String roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
    }

    public static List<RoomDTO> fromRooms(List<Room> rooms) {
        return rooms
                .stream()
                .map(room -> new RoomDTO(
                        room.getRoomNumber(),
                        room.getNumberOfBeds()))
                .collect(Collectors.toList());
    }
}
