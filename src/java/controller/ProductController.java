package controller;

import dao.CompanyDao;
import dao.ProductDao;
import entity.Company;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ProductController extends HttpServlet {

    private static final ProductDao productDao = new ProductDao();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentControllerServlet at "
                    + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();

            if (path == null) {
                path = "/";
            }
            switch (path) {
                case "/":
                    getProducts(request, response);
                    break;
         
                case "/detail":
                    productDetail(request, response);
                    break;
                case "/delete":
                    deleteProduct(request, response);
                    break;
                default:
                    break;
              
            }
        } catch (Exception ex) {

            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            System.out.println("pat" + path);
            if ("/addProduct".equals(path)) {
                addProduct(request, response);
            } else if ("/updateProduct".equals(path)) {
                updateProduct(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getProducts(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Product> list = productDao.getProducts();
        request.setAttribute("productList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //read student info from the form
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description =request.getParameter("description");
        String detail = request.getParameter("detail");
        String amount = request.getParameter("amount");
        String discount = request.getParameter("discount");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String image = request.getParameter("image");
        String company = request.getParameter("company");
        System.out.println("ss  "+ description);

        Company comp = new CompanyDao().getCompany(company);
        Product product = new Product(name, Integer.parseInt(price), description, detail, Integer.parseInt(amount), Integer.parseInt(discount), color, Integer.parseInt(size), image, comp);
        System.out.println(product);
        productDao.addProduct(product);
        response.sendRedirect(request.getContextPath()+ "/admin");
    }

    private void productDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        String productId = request.getParameter("productId");
//        Product product = productDao.getProdut(productId);
//        request.setAttribute("productDetail", product);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
//        dispatcher.forward(request, response);
        String productId = request.getParameter("productId");
        Product product = new ProductDao().getProdut(productId);
        List<Product> list = new ProductDao().getProducts();
        
        
        request.setAttribute("productDetail", product);
        request.setAttribute("productList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../detail.jsp");
                

        dispatcher.forward(request, response);
        
        
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read student info from the form data
        int id = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String detail = request.getParameter("detail");
        String amount = request.getParameter("amount");
        String discount = request.getParameter("discount");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String image = request.getParameter("image");
        
        String company = request.getParameter("company");

        Company comp = new CompanyDao().getCompany(company);
        Product product = new Product(id, name, Integer.parseInt(price), description, detail, Integer.parseInt(amount), Integer.parseInt(discount), color, Integer.parseInt(size), image, comp);

        productDao.updateProduct(product);
        
        response.sendRedirect( request.getContextPath()+"/admin");

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student id from the form data
        String productId = request.getParameter("productId");
        // delete student from the database
        productDao.deleteProduct(productId);
        // send them back to the "list student" pages
        response.sendRedirect( request.getContextPath()+"/admin");

    }
}
