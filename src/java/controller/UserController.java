package controller;

import dao.UsersDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import java.util.logging.Level;

public class UserController extends HttpServlet {

    private static final UsersDao usersDao = new UsersDao();

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
            if(path == null){
                path="/";
            }
            switch(path){
                case "/":
                    List<User> list = usersDao.getUsers();
                    // add these students to the object request
                    request.setAttribute("userslist", list);
                    // send to the JSP page (view)
                    request.getRequestDispatcher("list.jsp").forward(request, response);
                    break;
                case "/logout":
                    logout(request,response);
                    break;
                case "/login":
                    request.getRequestDispatcher("../login.jsp").forward(request, response);
                    break;
                case "/register":
                    request.getRequestDispatcher("../register.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
//            if(path == null){
//                List<User> list = usersDao.getUsers();
//            // add these students to the object request
//            request.setAttribute("userslist", list);
//            // send to the JSP page (view)
//            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
//            dispatcher.forward(request, response);
//            }else if("/logout".equals(path)){
//                logout(request,response);
//            }else if("/login".equals(path){  
//                request.getRequestDispatcher("../login.jsp").forward(request, response);
//
//            }else if("/register".equals(path)){
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//            }
            
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            String path = request.getPathInfo();
            switch(path){
                case "/login":
                    login(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                default:
                    break;
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(email, password);
        if (usersDao.login(user).getId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", user);
            request.setAttribute("message", "Login success");
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("userRegister", user);
            request.setAttribute("loginFail", "User name or password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cf_password = request.getParameter("cf_password");
        if (!password.equals(cf_password)) {
            request.setAttribute("loginFail", "User name or password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            User user = new User(name, email, password);
            System.out.println("a " + usersDao.register(user));
            
            if (usersDao.register(user).getId()!= 0) {
                HttpSession session = request.getSession();
                System.out.println("ss" + usersDao.getUser(Integer.toString(user.getId())));
                user = usersDao.getUser(Integer.toString(user.getId()));
                session.setAttribute("userLogin", user);
                
                request.setAttribute("message", "Login success");
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("userRegister", user);
                request.setAttribute("loginFail", "User name or password is incorrect");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }

    }
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/");
    }   

}
