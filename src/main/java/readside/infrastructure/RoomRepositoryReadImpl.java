package readside.infrastructure;

import org.springframework.stereotype.Component;
import readside.domain.AvailableRoom;
import readside.domain.api.RoomRepositoryRead;
import writeside.domain.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RoomRepositoryReadImpl implements RoomRepositoryRead {

    private final List<AvailableRoom> availableRooms = new ArrayList<>();

    public RoomRepositoryReadImpl() {
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
    public List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) {

        List<String> freeRooms = new ArrayList<>();
        int numberOfBedsNeeded = numberOfGuests;

        for (AvailableRoom availableRoom : availableRooms)
        {
            if (availableRoom.isFree(fromDate, toDate) && availableRoom.getNumberOfBeds() <= numberOfBedsNeeded) {
                freeRooms.add(availableRoom.getRoomNumber());
                numberOfBedsNeeded -= availableRoom.getNumberOfBeds();
            }

            if (numberOfBedsNeeded == 0)
                break;

        }

        if (numberOfBedsNeeded > 0) {
            return Collections.emptyList();
        }

        return freeRooms;
    }
}
