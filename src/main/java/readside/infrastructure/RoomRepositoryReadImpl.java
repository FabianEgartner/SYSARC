package readside.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import readside.domain.AvailableRoom;
import readside.domain.api.RoomRepositoryRead;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class RoomRepositoryReadImpl implements RoomRepositoryRead {

    private final List<AvailableRoom> availableRooms = new ArrayList<>();

    public RoomRepositoryReadImpl() {
        availableRooms.add(new AvailableRoom("100", 1));
        availableRooms.add(new AvailableRoom("101", 1));
        availableRooms.add(new AvailableRoom("102", 1));
        availableRooms.add(new AvailableRoom("103", 1));
        availableRooms.add(new AvailableRoom("104", 1));
        availableRooms.add(new AvailableRoom("105", 1));
        availableRooms.add(new AvailableRoom("106", 1));
        availableRooms.add(new AvailableRoom("107", 1));
        availableRooms.add(new AvailableRoom("108", 1));

        availableRooms.add(new AvailableRoom("200", 2));
        availableRooms.add(new AvailableRoom("201", 2));
        availableRooms.add(new AvailableRoom("202", 2));
        availableRooms.add(new AvailableRoom("203", 2));
        availableRooms.add(new AvailableRoom("204", 2));
        availableRooms.add(new AvailableRoom("205", 2));
        availableRooms.add(new AvailableRoom("206", 2));
        availableRooms.add(new AvailableRoom("207", 2));
        availableRooms.add(new AvailableRoom("208", 2));

        availableRooms.add(new AvailableRoom("300", 3));
        availableRooms.add(new AvailableRoom("301", 3));
        availableRooms.add(new AvailableRoom("302", 3));
        availableRooms.add(new AvailableRoom("303", 3));
        availableRooms.add(new AvailableRoom("304", 3));
        availableRooms.add(new AvailableRoom("305", 3));
        availableRooms.add(new AvailableRoom("306", 3));
        availableRooms.add(new AvailableRoom("307", 3));
        availableRooms.add(new AvailableRoom("308", 3));

        availableRooms.add(new AvailableRoom("400", 4));
        availableRooms.add(new AvailableRoom("401", 4));
        availableRooms.add(new AvailableRoom("402", 4));
        availableRooms.add(new AvailableRoom("403", 4));
        availableRooms.add(new AvailableRoom("404", 4));
        availableRooms.add(new AvailableRoom("405", 4));
        availableRooms.add(new AvailableRoom("406", 4));
        availableRooms.add(new AvailableRoom("407", 4));
        availableRooms.add(new AvailableRoom("408", 4));
    }

    @Override
    public List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {

        List<String> freeRooms = new ArrayList<>();
        int numberOfBedsNeeded = numberOfGuests;

        for (AvailableRoom availableRoom : availableRooms)
        {
            if (availableRoom.isFree(fromDate, toDate)) {
                freeRooms.add(availableRoom.getRoomNumber());
                numberOfBedsNeeded -= availableRoom.getNumberOfBeds();
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
    public List<AvailableRoom> getAvailableRooms() {
        return availableRooms;
    }
}
