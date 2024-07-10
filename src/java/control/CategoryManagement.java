/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CategoryDAO;
import java.io.IOException;
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

public class CategoryManagement extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";

    private CategoryDAO categoryDao = new CategoryDAO();

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
                        deleteCategory(request, response);
                        break;
                    default:
                        showListCategory(request, response);
                        break;
                }
            } else {
                showListCategory(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryManagement.class.getName()).log(Level.SEVERE, null, ex);
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
                            insertCategory(request, response);
                            break;
                        case "update":
                            updateCategory(request, response);
                            break;
                        default:
                            showListCategory(request, response);
                            break;
                    }
                } else {
                    showListCategory(request, response);
                }
            } else {
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("This Error is in Category Servlet" + ex.getMessage());
        }
    }

    private void showListCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategories = categoryDao.listAll();
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("category-list.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("category-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        Category category = categoryDao.getObjectById(typeId);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        int result = 0;
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        result = categoryDao.deleteRec(typeId);
        System.out.println("ket qua delete: " + result);
        response.sendRedirect(request.getContextPath() + "/category-management");
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String categoryName = request.getParameter("categoryName");
        String memo = request.getParameter("memo");

        Category newCategory = new Category(0, categoryName, memo); // typeId is auto-generated
        categoryDao.insertRec(newCategory);
        response.sendRedirect(request.getContextPath() + "/category-management");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String categoryName = request.getParameter("categoryName");
        String memo = request.getParameter("memo");

        Category updatedCategory = new Category(typeId, categoryName, memo);
        categoryDao.updateRec(updatedCategory);
        response.sendRedirect(request.getContextPath() + "/category-management");
    }
}
