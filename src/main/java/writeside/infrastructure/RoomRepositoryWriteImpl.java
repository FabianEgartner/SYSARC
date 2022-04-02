package writeside.infrastructure;

import org.springframework.stereotype.Component;
import writeside.domain.Room;
import writeside.domain.api.RoomRepositoryWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoomRepositoryWriteImpl implements RoomRepositoryWrite {

    private final List<Room> rooms = new ArrayList<>();

    public RoomRepositoryWriteImpl() {
        rooms.add(new Room("100", 1));
        rooms.add(new Room("101", 1));
        rooms.add(new Room("102", 1));
        rooms.add(new Room("103", 1));

        rooms.add(new Room("200", 2));
        rooms.add(new Room("201", 2));
        rooms.add(new Room("202", 2));
        rooms.add(new Room("203", 2));

        rooms.add(new Room("300", 3));
        rooms.add(new Room("301", 3));
        rooms.add(new Room("302", 3));
        rooms.add(new Room("303", 3));
    }

    @Override
    public Optional<Room> getRoom(String roomNumber) {

        for (Room room : rooms)
        {
            if (room.getRoomNumber().equals(roomNumber) && room.getRoomStatus().equals(RoomStatus.FREE))
                return Optional.of(room);
        }

        return Optional.empty();
    }

}
