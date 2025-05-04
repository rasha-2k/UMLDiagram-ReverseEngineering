/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I3.DatabaseOperation;

import I3.Classes.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CustomerDb {
    private final Connection conn;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public CustomerDb() {
        conn = DataBaseConnection.connectTODB();
    }

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
