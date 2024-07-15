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

public class AccountManagement extends HttpServlet {

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
            if (action != null) {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteAccount(request, response);
                        break;
                    case "search":
                        break;
                    default:
                        showListAccount(request, response);
                        break;
                }
            } else {
                showListAccount(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        try {

            if (action != null) {
                switch (action) {
                    case "insert":
                        insertAccount(request, response);
                        break;
                    case "update":
                        updateAccount(request, response);
                        break;
                    default:
                        request.getRequestDispatcher(ERROR).forward(request, response);
                        break;
                }
            } else {
                showListAccount(request, response);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("This Error is in Account Servlet" + ex.getMessage());
        }
    }

    private void showListAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Account> listAcc = accDao.listAll();
        request.setAttribute("listAccount", listAcc);
        request.getRequestDispatcher("account-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("account-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String accName = request.getParameter("accName");
        Account acc = accDao.getObjectById(accName);
        request.setAttribute("account", acc);
        RequestDispatcher dispatcher = request.getRequestDispatcher("account-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String accName = request.getParameter("accName");
        accDao.deleteRec(accName);
        response.sendRedirect(request.getContextPath() + "/account-management");
    }

    private void insertAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        boolean isUse = Boolean.parseBoolean(request.getParameter("isUse"));
        int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));

        Account newAccount = new Account(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
        accDao.insertRec(newAccount);
        response.sendRedirect(request.getContextPath() + "/account-management");
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        boolean isUse = Boolean.parseBoolean(request.getParameter("isUse"));
        int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));

        Account updatedAccount = new Account(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
        accDao.updateRec(updatedAccount);
        response.sendRedirect(request.getContextPath() + "/account-management");
    }

}
