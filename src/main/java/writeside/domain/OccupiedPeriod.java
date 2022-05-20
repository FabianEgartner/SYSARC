package writeside.domain;

import java.time.LocalDate;

public class OccupiedPeriod {

    private final LocalDate fromDate;
    private final LocalDate toDate;

    public OccupiedPeriod(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
