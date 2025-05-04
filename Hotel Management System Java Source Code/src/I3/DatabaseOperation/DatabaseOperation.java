package I3.DatabaseOperation;

import I3.Classes.Food;
import I3.Classes.Item;
import I3.Classes.Room;
import I3.Classes.RoomFare;
import I3.Classes.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseOperation {

    private final Connection conn = DataBaseConnection.connectTODB();
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void insertCustomer(UserInfo user) {
        String insertQuery = "INSERT INTO userInfo (name, address, phone, type) VALUES (?, ?, ?, ?)";
        try {
            statement = conn.prepareStatement(insertQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhoneNo());
            statement.setString(4, user.getType());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted new Customer");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void updateCustomer(UserInfo user) {
        String updateQuery = "UPDATE userInfo SET name = ?, address = ?, phone = ?, type = ? WHERE user_id = ?";
        try {
            statement = conn.prepareStatement(updateQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhoneNo());
            statement.setString(4, user.getType());
            statement.setInt(5, user.getCustomerId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated Customer");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void deleteCustomer(int userId) {
        String deleteQuery = "DELETE FROM userInfo WHERE user_id = ?";
        try {
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, userId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted user");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getAllCustomer() {
        String query = "SELECT * FROM userInfo";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all customers");
        }
        return result;
    }

    public ResultSet searchUser(String user) {
        String query = "SELECT user_id, name, address FROM userInfo WHERE name LIKE ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + user + "%");
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in search user function");
        }
        return result;
    }

    public ResultSet searchAnUser(int id) {
        String query = "SELECT * FROM userInfo WHERE user_id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in returning a user function");
        }
        return result;
    }

    public ResultSet getAvailableRooms(long check_inTime) {
        String query = "SELECT room_no FROM room LEFT OUTER JOIN booking ON room.room_no = booking.booking_room "
                + "WHERE booking.booking_room IS NULL OR ? < booking.check_in OR booking.check_out < ? "
                + "GROUP BY room.room_no ORDER BY room_no";
        try {
            statement = conn.prepareStatement(query);
            statement.setLong(1, check_inTime);
            statement.setLong(2, check_inTime);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in returning free rooms");
        }
        return result;
    }

    public ResultSet getBookingInfo(long start_date, long end_date, String roomNo) {
        String query = "SELECT * FROM booking WHERE booking_room = ? AND ("
                + "(check_in <= ? AND (check_out = 0 OR check_out <= ?)) OR "
                + "(check_in > ? AND check_out < ?) OR "
                + "(check_in <= ? AND (check_out = 0 OR check_out > ?)))";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, roomNo);
            statement.setLong(2, start_date);
            statement.setLong(3, end_date);
            statement.setLong(4, start_date);
            statement.setLong(5, end_date);
            statement.setLong(6, end_date);
            statement.setLong(7, end_date);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in returning booking info");
        }
        return result;
    }

    public int getCustomerId(UserInfo user) {
        int id = -1;
        String query = "SELECT user_id FROM userInfo WHERE name = ? AND phone = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoneNo());
            result = statement.executeQuery();
            if (result.next()) {
                id = result.getInt("user_id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in returning user ID");
        }
        return id;
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
}
