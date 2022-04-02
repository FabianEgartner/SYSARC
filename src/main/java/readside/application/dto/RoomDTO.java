package readside.application.dto;

import writeside.domain.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDTO {

    private final String roomNumber;
    private final int numberOfBeds;
    private final RoomStatus roomStatus;

    public RoomDTO(String roomNumber, int numberOfBeds, RoomStatus roomStatus) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomStatus = roomStatus;
    }

    public static List<RoomDTO> fromRooms(List<Room> rooms) {
        return rooms
                .stream()
                .map(room -> new RoomDTO(
                        room.getRoomNumber(),
                        room.getNumberOfBeds(),
                        room.getRoomStatus()))
                .collect(Collectors.toList());
    }
}
