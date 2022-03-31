package writeside.domain;

public class Room {

    private String roomNumber;
    private RoomStatus roomStatus;


    public Room(String roomNumber, RoomStatus roomStatus) {
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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
                ", roomStatus=" + roomStatus +
                '}';
    }
}
