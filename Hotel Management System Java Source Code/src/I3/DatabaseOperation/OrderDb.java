package I3.DatabaseOperation;

import I3.Classes.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class OrderDb {

    private final Connection conn = DataBaseConnection.connectTODB();
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void insertOrder(Order order) {
        String insertOrder = "INSERT INTO orderItem (booking_id, item_food, price, quantity, total) VALUES (?, ?, ?, ?, ?)";
        try {
            statement = conn.prepareStatement(insertOrder);
            statement.setInt(1, order.getBookingId());
            statement.setString(2, order.getFoodItem());
            statement.setDouble(3, order.getPrice());
            statement.setInt(4, order.getQuantity());
            statement.setDouble(5, order.getTotal());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully inserted a new Order");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nOrder Failed");
        } finally {
            flushStatementOnly();
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
