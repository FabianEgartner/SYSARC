package readside.domain;

import java.time.LocalDate;
import java.time.Period;
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

    public boolean isFree(LocalDate fromDate, LocalDate toDate)
    {
        for (OccupiedPeriod period : occupiedPeriods)
        {
            if (!(toDate.isBefore(period.getFromDate()) || fromDate.isAfter(period.getToDate())))
                return false;
        }

        return true;
    }
}