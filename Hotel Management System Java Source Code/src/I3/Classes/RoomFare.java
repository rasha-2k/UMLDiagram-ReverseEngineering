package I3.Classes;

public class RoomFare {

    private String roomType;
    private int pricePerDay;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "RoomFare{" +
                "roomType='" + roomType + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
