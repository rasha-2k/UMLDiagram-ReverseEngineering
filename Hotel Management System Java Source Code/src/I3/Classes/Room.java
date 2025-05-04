package I3.Classes;

public class Room {
    private int roomId;
    private String roomNo;
    private int bedNumber;

    private boolean hasTV;
    private boolean hasWiFi;
    private boolean hasGizer;
    private boolean hasPhone;

    private RoomFare roomClass;

    public Room(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean hasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean hasWiFi() {
        return hasWiFi;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    public boolean hasGizer() {
        return hasGizer;
    }

    public void setHasGizer(boolean hasGizer) {
        this.hasGizer = hasGizer;
    }

    public boolean hasPhone() {
        return hasPhone;
    }

    public void setHasPhone(boolean hasPhone) {
        this.hasPhone = hasPhone;
    }

    public RoomFare getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomFare roomClass) {
        this.roomClass = roomClass;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNo='" + roomNo + '\'' +
                ", bedNumber=" + bedNumber +
                ", hasTV=" + hasTV +
                ", hasWiFi=" + hasWiFi +
                ", hasGizer=" + hasGizer +
                ", hasPhone=" + hasPhone +
                ", roomClass=" + roomClass +
                '}';
    }
}
