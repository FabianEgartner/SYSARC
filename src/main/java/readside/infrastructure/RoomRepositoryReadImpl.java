package readside.infrastructure;

import org.springframework.stereotype.Repository;
import writeside.domain.Room;
import readside.domain.api.RoomRepositoryRead;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class RoomRepositoryReadImpl implements RoomRepositoryRead {

    private static final List<Room> rooms = new ArrayList<>();
    private static RoomRepositoryReadImpl instance;

    public static RoomRepositoryReadImpl getInstance()
    {
        if (null == RoomRepositoryReadImpl.instance) {
            new RoomRepositoryReadImpl();
        }

        return RoomRepositoryReadImpl.instance;
    }

    private RoomRepositoryReadImpl() {
        RoomRepositoryReadImpl.instance = this;

        rooms.add(new Room("100", 1));
        rooms.add(new Room("101", 1));
        rooms.add(new Room("102", 1));
        rooms.add(new Room("103", 1));
        rooms.add(new Room("104", 1));
        rooms.add(new Room("105", 1));
        rooms.add(new Room("106", 1));
        rooms.add(new Room("107", 1));
        rooms.add(new Room("108", 1));

        rooms.add(new Room("200", 2));
        rooms.add(new Room("201", 2));
        rooms.add(new Room("202", 2));
        rooms.add(new Room("203", 2));
        rooms.add(new Room("204", 2));
        rooms.add(new Room("205", 2));
        rooms.add(new Room("206", 2));
        rooms.add(new Room("207", 2));
        rooms.add(new Room("208", 2));

        rooms.add(new Room("300", 3));
        rooms.add(new Room("301", 3));
        rooms.add(new Room("302", 3));
        rooms.add(new Room("303", 3));
        rooms.add(new Room("304", 3));
        rooms.add(new Room("305", 3));
        rooms.add(new Room("306", 3));
        rooms.add(new Room("307", 3));
        rooms.add(new Room("308", 3));

        rooms.add(new Room("400", 4));
        rooms.add(new Room("401", 4));
        rooms.add(new Room("402", 4));
        rooms.add(new Room("403", 4));
        rooms.add(new Room("404", 4));
        rooms.add(new Room("405", 4));
        rooms.add(new Room("406", 4));
        rooms.add(new Room("407", 4));
        rooms.add(new Room("408", 4));
    }

    @Override
    public List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {

        List<String> freeRooms = new ArrayList<>();
        int numberOfBedsNeeded = numberOfGuests;

        for (Room room : rooms)
        {
            if (room.isFree(fromDate, toDate)) {
                freeRooms.add(room.getRoomNumber());
                numberOfBedsNeeded -= room.getNumberOfBeds();
            }

            if (numberOfBedsNeeded <= 0)
                break;

        }

        if (numberOfBedsNeeded > 0) {
            return Collections.emptyList();
        }

        return freeRooms;
    }

    @Override
    public List<Room> getRooms() {
        return RoomRepositoryReadImpl.rooms;
    }
}
