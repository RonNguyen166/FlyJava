/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDao;
import dao.OrderItemsDao;
import dao.OrdersDao;
import dao.PaymentDao;
import dao.ProductDao;
import entity.Cart;
import entity.OrderItems;
import entity.Orders;
import entity.Product;
import entity.User;
import entity.Payment;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class CartController extends HttpServlet {

    private static final CartDao cartDao = new CartDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/users/login");
        }
        try {
            String path = request.getPathInfo();
            if (path == null) {
                path = "/";
            }
            switch(path){
                case"/":
                    List<Payment> payList = new PaymentDao().getPayments();
                    System.out.println("s"+ payList);
                    request.setAttribute("payList", payList);
                    List<Cart> list = cartDao.getCartsUser(user);
                    int total = cartDao.total(Integer.toString(user.getId()));
                    request.setAttribute("cartList", list);
                    request.setAttribute("total", total);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "/update-quantity":
                    updateQuantity(request, response);
                    break;
                case "/add":
                    addCart(request, response,user);
                    break;
                case "/delete":
                    deleteCart(request, response);
                    break;
                default:
                    break;
            }
       
        } catch (Exception ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            String path = request.getPathInfo();
            if (path == null) {
                path = "/";
            }
            switch (path) {
                case "/checkout":
                    checkout(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath()+ "/cart");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cartId = request.getParameter("cartId");
        cartDao.deleteCart(cartId);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void addCart(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
      
        String productId = request.getParameter("productId");
//        String quantity = request.getParameter("quantity");
        int quantity = 1;
        Product product = new ProductDao().getProdut(productId);
        cartDao.addCart(product, user, quantity);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        cartDao.updateQuantity(productId, Integer.parseInt(quantity));
        response.sendRedirect("/cart");

    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");
        String paymentId = request.getParameter("paymentId");
        Payment payment = new PaymentDao().getPayment(paymentId);
        int total = cartDao.total(Integer.toString(user.getId()));

        Orders order = new Orders(user, payment, total);
        order = new OrdersDao().addOrder(order);

        List<Cart> ls = cartDao.getCartsUser(user);
        for (Cart cart : ls) {
            Product product = new ProductDao().getProdut(Integer.toString(cart.getProduct().getId()));
            if (cart.getProduct().getId() == product.getId()) {
                OrderItems orderItem = new OrderItems(product, order, cart.getQuantity(), product.getPrice());
                new OrderItemsDao().addOrderItem(orderItem);
            }

        }
        cartDao.deleteCartUser(user);
        response.sendRedirect(request.getContextPath() + "/home");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
