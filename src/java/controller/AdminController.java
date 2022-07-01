/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CompanyDao;
import dao.OrderItemsDao;
import dao.ProductDao;
import dao.UsersDao;
import entity.Company;
import entity.OrderItems;
import entity.Product;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null) {
            path = "/";
        }
        List<Company> listC = new CompanyDao().getCompanies();

        switch (path) {
            case "/products":
                getProducts(request, response);
                break;
            case "/users":
                getUsers(request, response);
                break;
            case "/orders":
                break;
            case "/products/add":
                request.setAttribute("companyList", listC);
                request.getRequestDispatcher("../../addProduct.jsp").forward(request, response);
                break;
            case "/products/edit":
                String productId = request.getParameter("productId");
                Product product = new ProductDao().getProdut(productId);
                request.setAttribute("productEdit", product);
                request.setAttribute("companyList", listC);
                request.getRequestDispatcher("../../editProduct.jsp").forward(request, response);
                break;
            default:
                getProducts(request, response);

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void getProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listP = new ProductDao().getProducts();
        request.setAttribute("productList", listP);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
    private void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> list = new UsersDao().getUsers();
        request.setAttribute("userList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../admin.jsp");
        dispatcher.forward(request, response);
    }
//    private void getOrders(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<OrderItems> list = new OrderItemsDao().getOrderItems();
//        request.setAttribute("list", list);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
//        dispatcher.forward(request, response);
//    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
