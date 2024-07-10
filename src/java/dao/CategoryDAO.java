/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import utilities.ConnectDB;

public class CategoryDAO {

    private static final String INSERT_CATEGORY = "INSERT INTO [dbo].[categories] (categoryName, memo) VALUES (?,?);";
    private static final String UPDATE_CATEGORY = "UPDATE categories SET categoryName = ?, memo = ? WHERE typeId = ?;";
    private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE typeId = ?;";
    private static final String GET_CATEGORY = "SELECT * FROM categories WHERE typeId = ?;";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories;";

    public int insertRec(Category category) {
        int result = 0;
        try (Connection conn = new ConnectDB().getConnection(); 
             PreparedStatement st = conn.prepareStatement(INSERT_CATEGORY)) {
            st.setString(1, category.getCategoryName());
            st.setString(2, category.getMemo());
            result = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateRec(Category category) {
        int result = 0;
        try (Connection conn = new ConnectDB().getConnection(); 
             PreparedStatement st = conn.prepareStatement(UPDATE_CATEGORY)) {
            st.setString(1, category.getCategoryName());
            st.setString(2, category.getMemo());
            st.setInt(3, category.getTypeId());
            result = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int deleteRec(int id) {
        int res = 0;
        try (Connection conn = new ConnectDB().getConnection(); 
            PreparedStatement st = conn.prepareStatement(DELETE_CATEGORY)) {
            st.setInt(1, id);
            res = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    

    public Category getObjectById(int id) {
        Category currentCategory = null;
        try (Connection conn = new ConnectDB().getConnection(); 
             PreparedStatement st = conn.prepareStatement(GET_CATEGORY)) {
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                int typeId = res.getInt("typeId");
                String categoryName = res.getString("categoryName");
                String memo = res.getString("memo");

                currentCategory = new Category(typeId, categoryName, memo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currentCategory;
    }

    public List<Category> listAll() {
        List<Category> list = new ArrayList<>();
        try (Connection conn = new ConnectDB().getConnection(); 
             PreparedStatement st = conn.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet res = st.executeQuery();
            while (res.next()) {
                int typeId = res.getInt("typeId");
                String categoryName = res.getString("categoryName");
                String memo = res.getString("memo");

                list.add(new Category(typeId, categoryName, memo));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
