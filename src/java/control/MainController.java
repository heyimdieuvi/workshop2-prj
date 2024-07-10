/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;

/**
 * MainController handles main application requests.
 */
public class MainController extends HttpServlet {

    private CategoryDAO categoryDao = new CategoryDAO();
    private ProductDAO productDao = new ProductDAO();

    private static final String ERROR = "error.jsp";
    private static final String HOME = "Home.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String ADMIN = "admin";
    private static final String MANAGER = "manager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false); // Retrieve existing session, if any
            
            if (action != null) {
                switch (action) {
                    case "login":
                        login(request, response);
                        break; // Avoid forwarding twice
                    case "logout":
                        logout(request, response, session);
                        break; // Avoid forwarding twice
                    default:
                        request.getRequestDispatcher(url).forward(request, response);
                        break;
                }
                //if dont have any action -> show home page
            } else {
                showMain(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN;
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false); // Retrieve existing session, if any, if not return null
            if (action != null) {
                switch (action) {
                    case "login":
                        request.getRequestDispatcher(url).forward(request, response);
                        break; // Avoid forwarding twice
                    case "logout":
                        logout(request, response, session);
                        break; // Avoid forwarding twice
                    default:
                        request.getRequestDispatcher(HOME).forward(request, response);
                        break;
                }
            } else {
                showMain(request, response);
            }     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accDao = new AccountDAO();
        String message;
        Account validAccount = null;
        String acc = request.getParameter("account");
        String pass = request.getParameter("password");
        try {
            Account checkAcc = accDao.checkExistAccount(acc, pass);
            if (checkAcc != null && checkAcc.isIsUse()) {
                
                HttpSession session = request.getSession();
                session.setAttribute("account", checkAcc);
                
                Cookie a = new Cookie("acc", checkAcc.getAccount());
                Cookie p = new Cookie("pass", checkAcc.getPass());
                a.setMaxAge(50);
                p.setMaxAge(50);
                response.addCookie(p);
                response.addCookie(a);
                //session.setAttribute("loginRole", checkAcc.getRoleInSystem());
                response.sendRedirect(ADMIN);
            } else if (checkAcc != null && checkAcc.isIsUse() == false) {
                message = "This account is deactive!!!";
                request.setAttribute("message", message);
                request.getRequestDispatcher(LOGIN).forward(request, response);
            } else {
                message = "Wrong email or password!!!";
                request.setAttribute("message", message);
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(LOGIN);
    }

    private void showMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listCategories = categoryDao.listAll();
        request.setAttribute("listCategories", listCategories);

        List<Product> listProducts = productDao.listAll();
        request.setAttribute("listProducts", listProducts);

        request.getRequestDispatcher(HOME).forward(request, response);
    }
}
