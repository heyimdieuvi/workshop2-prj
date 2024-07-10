/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;

public class ProductManagement extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";

    private ProductDAO productDao = new ProductDAO();
    private CategoryDAO cateDao = new CategoryDAO();
    private AccountDAO accDao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false);
            if (action != null) {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteProduct(request, response);
                        break;
                    default:
                        showListProduct(request, response);
                        break;
                }
            } else {
                showListProduct(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false);
            if ((Account) session.getAttribute("account") != null) {
                if (action != null) {
                    switch (action) {
                        case "insert":
                            insertProduct(request, response);
                            break;
                        case "update":
                            updateProduct(request, response);
                            break;
                        default:
                            showListProduct(request, response);
                            break;
                    }
                } else {
                    showListProduct(request, response);
                }
            } else {
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("This Error is in Product Servlet" + ex.getMessage());
        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProducts = productDao.listAll();
        request.setAttribute("listProducts", listProducts);
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("product-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String productId = request.getParameter("productId");
        Product product = productDao.getObjectById(productId);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String productId = request.getParameter("productId");
        productDao.deleteRec(productId);
        response.sendRedirect(request.getContextPath() + "/product-management");
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productImage = request.getParameter("productImage");
        String brief = request.getParameter("brief");
        Date postedDate = Date.valueOf(request.getParameter("postedDate"));
        System.out.println(request.getParameter("postedDate"));
        System.out.println(postedDate);
//        Date postedDate = 
        Category type = cateDao.getObjectById(Integer.parseInt(request.getParameter("typeId"))); // Assuming you'll set the category later
        Account account = accDao.getObjectById(request.getParameter("account"));
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));

        Product newProduct = new Product(productId, productName, productImage, brief, postedDate, type, account, unit, price, discount);
        int kq = productDao.insertRec(newProduct);
        System.out.println(kq);
        response.sendRedirect(request.getContextPath() + "/product-management");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productImage = request.getParameter("productImage");
        String brief = request.getParameter("brief");
        Date postedDate = Date.valueOf(request.getParameter("postedDate"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        Category type = cateDao.getObjectById(typeId); // Assuming you'll set the category later
        Account account = accDao.getObjectById(request.getParameter("account")); // Assuming you'll set the account later
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));

        Product updatedProduct = new Product(productId, productName, productImage, brief, postedDate, type, account, unit, price, discount);
        productDao.updateRec(updatedProduct);
        response.sendRedirect(request.getContextPath() + "/product-management");
    }
}
