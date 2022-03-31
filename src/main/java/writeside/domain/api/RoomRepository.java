package writeside.domain.api;

import writeside.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Optional<Room> getRoom(String roomNumber);
//    void createRoom(String roomNumber, int availableBeds);
}
