package readside.infrastructure;

import readside.domain.AvailableRoom;
import readside.domain.api.RoomRepository;
import writeside.domain.Room;
import writeside.domain.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private List<AvailableRoom> availableRooms = new ArrayList<>();

    public RoomRepositoryImpl() {
        availableRooms.add(new AvailableRoom("100", 1));
        availableRooms.add(new AvailableRoom("101", 1));
        availableRooms.add(new AvailableRoom("102", 1));
        availableRooms.add(new AvailableRoom("103", 1));

        availableRooms.add(new AvailableRoom("200", 2));
        availableRooms.add(new AvailableRoom("201", 2));
        availableRooms.add(new AvailableRoom("202", 2));
        availableRooms.add(new AvailableRoom("203", 2));

        availableRooms.add(new AvailableRoom("300", 3));
        availableRooms.add(new AvailableRoom("301", 3));
        availableRooms.add(new AvailableRoom("302", 3));
        availableRooms.add(new AvailableRoom("303", 3));
    }

    @Override
    public List<Room> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {

        List<Room> freeRooms = new ArrayList<>();

        for (AvailableRoom availableRoom : availableRooms)
        {
            if (availableRoom.isFree(fromDate, toDate))
                freeRooms.add(new Room(availableRoom.getRoomNumber(), availableRoom.getNumberOfBeds()));
        }

        return freeRooms;
    }
}
