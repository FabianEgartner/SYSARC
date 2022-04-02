package writeside.domain.api;

import writeside.domain.Room;

import java.util.Optional;

public interface RoomRepositoryWrite {

    Optional<Room> getRoom(String roomNumber);
}
