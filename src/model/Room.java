package model;

public class Room implements IRoom{
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public Double getRoomPrice(){
        return price;
    }
    public RoomType getRoomType(){
        return enumeration;
    }
    public boolean isFree(){
        return true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomNumber='" + roomNumber + '\'' +
                ", Price(in $)=" + price + " per night" +
                ", Type=" + enumeration +
                '}';
    }
}
