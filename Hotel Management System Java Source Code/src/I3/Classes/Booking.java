package I3.Classes;

import java.util.ArrayList;

public class Booking {

    // Constants for booking types
    public static final String RESERVED = "Reserved";
    public static final String CONFIRMED = "Confirmed";

    // Required objects
    private UserInfo customer;
    private ArrayList<Room> rooms;

    // Booking details
    private int bookingId;
    private long checkInDateTime;
    private long checkOutDateTime;
    private String bookingType;
    private int numberOfGuests;

    // Constructor
    public Booking() {
        this.customer = new UserInfo();
        this.rooms = new ArrayList<>();
        this.bookingId = -1;
        this.bookingType = RESERVED;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public long getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(long checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public long getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(long checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public UserInfo getCustomer() {
        return customer;
    }

    public void setCustomer(UserInfo customer) {
        this.customer = customer;
    }

    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms); // Return a copy to ensure encapsulation
    }

    // Methods for managing rooms
    public void addRoom(String roomNo) {
        rooms.add(new Room(roomNo));
    }

    public void removeRoom(String roomNo) {
        rooms.removeIf(room -> room.getRoomNo().equals(roomNo));
    }

    // Calculate the total fare for all rooms
    public int getRoomsFare() {
        return rooms.stream()
                    .mapToInt(room -> room.getRoomClass().getPricePerDay())
                    .sum();
    }
}
