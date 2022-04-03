package readside.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableRoom {

    private final String roomNumber;
    private final int numberOfBeds;
    private final List<OccupiedPeriod> occupiedPeriods = new ArrayList<>();


    public AvailableRoom(String roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

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
        AvailableRoom that = (AvailableRoom) o;
        return roomNumber.equals(that.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "AvailableRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", occupiedPeriods=" + occupiedPeriods +
                '}';
    }
}