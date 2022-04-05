package writeside.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {

    private final String roomNumber;
    private final int numberOfBeds;
    private final List<OccupiedPeriod> occupiedPeriods = new ArrayList<>();


    public Room(String roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public List<OccupiedPeriod> getOccupiedPeriods() { return occupiedPeriods; }

    public void addOccupiedPeriod(OccupiedPeriod occupiedPeriod) {
        occupiedPeriods.add(occupiedPeriod);
    }

    public boolean isFree(LocalDate fromDate, LocalDate toDate) {
        for (OccupiedPeriod occupiedPeriod : occupiedPeriods) {
            if (toDate.isAfter(occupiedPeriod.getFromDate()) && fromDate.isBefore(occupiedPeriod.getToDate()))
                return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        return roomNumber.equals(that.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", occupiedPeriods=" + occupiedPeriods +
                '}';
    }
}