package readside.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableRoom {

    private String roomNumber;
    private int numberOfBeds;
    private List<OccupiedPeriod> occupiedPeriods = new ArrayList<>();

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

    public void addOccupiedPeriod(OccupiedPeriod occupiedPeriod)
    {
        occupiedPeriods.add(occupiedPeriod);
    }

    public boolean isFree(LocalDate fromDate, LocalDate toDate)
    {
        for (OccupiedPeriod period : occupiedPeriods)
        {
            if (toDate.isAfter(period.getFromDate().plusDays(1)) && fromDate.isBefore(period.getToDate()))
                return false;
        }

        return true;
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