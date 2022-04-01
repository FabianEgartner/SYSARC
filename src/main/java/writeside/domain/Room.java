package writeside.domain;

import writeside.domain.valueobjects.RoomStatus;

public class Room {

    private String roomNumber;
    private int numberOfBeds;
    private RoomStatus roomStatus;

    public Room(String roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomStatus = RoomStatus.FREE;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", roomStatus=" + roomStatus +
                '}';
    }
}
