/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I3.DatabaseOperation;

import I3.Classes.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ItemDb {
    private final Connection conn = DataBaseConnection.connectTODB();
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void insertItem(Item item) {
        String insertItem = "INSERT INTO item (name, description, price) VALUES (?, ?, ?)";
        try {
            statement = conn.prepareStatement(insertItem);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new item");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsert query for item failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void updateItem(Item item) {
        String updateItem = "UPDATE item SET name = ?, price = ?, description = ? WHERE item_id = ?";
        try {
            statement = conn.prepareStatement(updateItem);
            statement.setString(1, item.getItemName());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getItemId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated item");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query for item failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getItems() {
        String query = "SELECT * FROM item";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all items");
        }
        return result;
    }

    public void deleteItem(int itemId) {
        String deleteQuery = "DELETE FROM item WHERE item_id = ?";
        try {
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, itemId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted item");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query for item failed");
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
