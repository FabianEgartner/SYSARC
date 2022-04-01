package readside.infrastructure;

import readside.domain.api.RoomRepository;
import writeside.domain.Room;
import writeside.domain.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private List<Room> rooms = new ArrayList<>();

    public RoomRepositoryImpl() {
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
    public List<Room> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {
        return null;
    }
}
