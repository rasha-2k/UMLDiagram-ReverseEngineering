/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I3.DatabaseOperation;

import I3.Classes.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FoodDb {

    private final Connection conn = DataBaseConnection.connectTODB();
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void insertFood(Food food) {
        String insertFood = "INSERT INTO food (name, price) VALUES (?, ?)";
        try {
            statement = conn.prepareStatement(insertFood);
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Food Type");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery of Food Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getFoods() {
        String query = "SELECT * FROM food";
        try {
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all food items");
        }
        return result;
    }

    public void updateFood(Food food) {
        String updateFood = "UPDATE food SET name = ?, price = ? WHERE food_id = ?";
        try {
            statement = conn.prepareStatement(updateFood);
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());
            statement.setInt(3, food.getFoodId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated Food");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query of Food Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void deleteFood(int foodId) {
        String deleteQuery = "DELETE FROM food WHERE food_id = ?";
        try {
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, foodId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted food");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query of Food Failed");
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
