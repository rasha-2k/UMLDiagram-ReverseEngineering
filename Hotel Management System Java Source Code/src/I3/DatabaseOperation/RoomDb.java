package I3.DatabaseOperation;

import I3.Classes.Room;
import I3.Classes.RoomFare;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RoomDb {
    private final Connection conn = DataBaseConnection.connectTODB();
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void insertRoom(Room room) {
        String insertQuery = "INSERT INTO room (room_no, bed_number, tv, wifi, gizer, phone, room_class) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = conn.prepareStatement(insertQuery);
            statement.setString(1, room.getRoomNo());
            statement.setInt(2, room.getBedNumber());
            statement.setString(3, boolToString(room.hasTV()));
            statement.setString(4, boolToString(room.hasWiFi()));
            statement.setString(5, boolToString(room.hasGizer()));
            statement.setString(6, boolToString(room.hasPhone()));
            statement.setString(7, room.getRoomClass().getRoomType());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Room");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery of Room Class Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getRooms() {
        String query = "SELECT * FROM room";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all rooms");
        }
        return result;
    }

    public int getNoOfRooms() {
        int rooms = -1;
        String query = "SELECT COUNT(room_no) AS noRoom FROM room";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            if (result.next()) {
                rooms = result.getInt("noRoom");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError counting rooms");
        }
        return rooms;
    }

    public ResultSet getAllRoomNames() {
        String query = "SELECT room_no FROM room";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all room numbers");
        }
        return result;
    }

    public void deleteRoom(int roomId) {
        String deleteQuery = "DELETE FROM room WHERE room_id = ?";
        try {
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, roomId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted room");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void updateRoom(Room room) {
        String updateQuery = "UPDATE room SET room_no = ?, bed_number = ?, tv = ?, wifi = ?, gizer = ?, phone = ?, room_class = ? WHERE room_id = ?";
        try {
            statement = conn.prepareStatement(updateQuery);
            statement.setString(1, room.getRoomNo());
            statement.setInt(2, room.getBedNumber());
            statement.setString(3, boolToString(room.hasTV()));
            statement.setString(4, boolToString(room.hasWiFi()));
            statement.setString(5, boolToString(room.hasGizer()));
            statement.setString(6, boolToString(room.hasPhone()));
            statement.setString(7, room.getRoomClass().getRoomType());
            statement.setInt(8, room.getRoomId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated a room");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void insertRoomType(RoomFare roomType) {
        String insertRoomTypeQuery = "INSERT INTO roomType (type, price) VALUES (?, ?)";
        try {
            statement = conn.prepareStatement(insertRoomTypeQuery);
            statement.setString(1, roomType.getRoomType());
            statement.setDouble(2, roomType.getPricePerDay());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Room Type");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getRoomType() {
        String query = "SELECT * FROM roomType";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all Room Types");
        }
        return result;
    }

    public void updateRoomType(RoomFare roomType) {
        String updateRoomTypeQuery = "UPDATE roomType SET price = ? WHERE type = ?";
        try {
            statement = conn.prepareStatement(updateRoomTypeQuery);
            statement.setDouble(1, roomType.getPricePerDay());
            statement.setString(2, roomType.getRoomType());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated a Room Type");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdateQuery failed");
        } finally {
            flushStatementOnly();
        }
    }

    public String boolToString(boolean value) {
        return value ? "true" : "false";
    }

    public void flushAll() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }

    private void flushStatementOnly() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }
}
