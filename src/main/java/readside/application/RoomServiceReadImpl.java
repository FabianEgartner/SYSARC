package readside.application;

import org.springframework.stereotype.Component;
import readside.application.api.RoomServiceRead;
import readside.domain.NotEnoughRoomsException;
import readside.domain.api.RoomRepositoryRead;
import readside.infrastructure.RoomRepositoryReadImpl;

import java.time.LocalDate;
import java.util.List;

@Component
public class RoomServiceReadImpl implements RoomServiceRead {

    private final RoomRepositoryRead roomRepositoryRead = new RoomRepositoryReadImpl();

    @Override
    public List<String> getFreeRooms(LocalDate fromDate, LocalDate toDate, int numberOfGuests) throws NotEnoughRoomsException {
        List<String> freeRooms = roomRepositoryRead.getFreeRooms(fromDate, toDate, numberOfGuests);

        if (freeRooms.isEmpty())
        {
            throw new NotEnoughRoomsException("not enough free rooms available!");
        }

        return freeRooms;
    }
}
