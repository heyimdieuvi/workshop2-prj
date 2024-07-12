/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Account;
import model.Category;
import model.Product;
import utilities.ConnectDB;

public class ProductDAO implements Serializable{

    private CategoryDAO cateDao = new CategoryDAO();
    private AccountDAO accDao = new AccountDAO();

    private ServletContext sc;
    private static final String INSERT_PRODUCT = "INSERT INTO [dbo].[products] (productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, productImage = ?, brief = ?, postedDate = ?, typeId = ?, account = ?, unit = ?, price = ?, discount = ? WHERE productId = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productId = ?;";
    private static final String GET_PRODUCT = "SELECT * FROM products WHERE productId = ?;";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products;";

    public int insertRec(Product product) {
        int result = 0;
        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement st = conn.prepareStatement(INSERT_PRODUCT)) {
            st.setString(1, product.getProductId());
            st.setString(2, product.getProductName());
            st.setString(3, product.getProductImage());
            st.setString(4, product.getBrief());
            st.setDate(5, product.getPostedDate());
            st.setInt(6, product.getType().getTypeId()); // Assuming category is stored as name
            st.setString(7, product.getAccount().getAccount()); // Assuming account is stored as account name
            st.setString(8, product.getUnit());
            st.setInt(9, product.getPrice());
            st.setInt(10, product.getDiscount());

            result = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateRec(Product product) {
        int result = 0;
        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement st = conn.prepareStatement(UPDATE_PRODUCT)) {
            st.setString(1, product.getProductName());
            st.setString(2, product.getProductImage());
            st.setString(3, product.getBrief());
            st.setDate(4, product.getPostedDate());
            st.setInt(5, product.getType().getTypeId()); // Assuming category is stored as name
            st.setString(6, product.getAccount().getAccount()); // Assuming account is stored as account name
            st.setString(7, product.getUnit());
            st.setInt(8, product.getPrice());
            st.setInt(9, product.getDiscount());
            st.setString(10, product.getProductId());

            result = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int deleteRec(String id) {
        int res = 0;
        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement st = conn.prepareStatement(DELETE_PRODUCT)) {
            st.setString(1, id);
            res = st.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public Product getObjectById(String id) {
        Product currentProduct = null;
        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement st = conn.prepareStatement(GET_PRODUCT)) {
            st.setString(1, id);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                String productId = res.getString("productId");
                String productName = res.getString("productName");
                String productImage = res.getString("productImage");
                String brief = res.getString("brief");
                Date postedDate = res.getDate("postedDate");
                Category type = cateDao.getObjectById(res.getInt("typeId"));
                Account account = accDao.getObjectById(res.getString("account"));
                String unit = res.getString("unit");
                int price = res.getInt("price");
                int discount = res.getInt("discount");

                currentProduct = new Product(productId, productName, productImage, brief, postedDate, type, account, unit, price, discount);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currentProduct;
    }

    public List<Product> listAll() {

        List<Product> list = new ArrayList<>();
        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement st = conn.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String productId = res.getString("productId");
                String productName = res.getString("productName");
                String productImage = res.getString("productImage");
                String brief = res.getString("brief");
                Date postedDate = res.getDate("postedDate");
                Category type = cateDao.getObjectById(res.getInt("typeId")); // Assuming category is retrieved and set later
                Account account = accDao.getObjectById(res.getString("account")); // Assuming account is retrieved and set later
                String unit = res.getString("unit");
                int price = res.getInt("price");
                int discount = res.getInt("discount");

                list.add(new Product(productId, productName, productImage, brief, postedDate, type, account, unit, price, discount));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
