package model;

public class Room implements IRoom{
    String roomNumber;
    Double roomPrice;
    RoomType roomType;

    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public Double getRoomPrice(){
        return roomPrice;
    }
    public RoomType getRoomType(){
        return roomType;
    }
    public boolean isFree(){
        return true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomNumber='" + roomNumber + '\'' +
                ", Price(in $)=" + roomPrice + " per night" +
                ", Type=" + roomType +
                '}';
    }
}
