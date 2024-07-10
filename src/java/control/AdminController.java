/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class AdminController extends HttpServlet {

    private CategoryDAO cateDao = new CategoryDAO();
    private ProductDAO productDao = new ProductDAO();

    private static final String ERROR = "error.jsp";
    private static final String HOME = "admin-home.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String ACCOUNT = "account-management";
    private static final String PRODUCT = "product-management";
    private static final String CATEGORY = "category-management";

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false); // Retrieve existing session, if any
            if (action != null) {
                switch (action) {
                    case "logout":
                        logout(request, response, session);
                        return; 
                    default:
                        showMain(request, response, session);                        break;
                }
            } else {
                showMain(request, response, session);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showMain(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Category> listCategories = cateDao.listAll();
        request.setAttribute("listCategories", listCategories);
        List<Product> listProducts = productDao.listAll();
        request.setAttribute("listProducts", listProducts); 
        request.getRequestDispatcher(HOME).forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        session.invalidate();
        response.sendRedirect(LOGIN);
    }

}
