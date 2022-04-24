/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.AccountDAO;
import dal.GroupDAO;
import dal.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Account;
import model.Groups;
import model.Groups;
import model.SendEmail;
import model.SendEmail;

/**
 *
 * @author HP
 */
@WebServlet(name = "LoadGroupsServlet", urlPatterns = {"/load"})
public class LoadGroupsServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadGroupsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadGroupsServlet at " + request.getContextPath() + "</h1>");
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
            GroupDAO GD = new GroupDAO();
            List<Groups> list = GD.getName();
            request.setAttribute("listG", list);
            request.getRequestDispatcher("newuser.jsp").forward(request, response);
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
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String Phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            int g = Integer.parseInt(gender);
            String position = request.getParameter("position");
            int p = Integer.parseInt(position);
            String groups = request.getParameter("groups");
            PrintWriter out = response.getWriter();
            AccountDAO dao = new AccountDAO();
            String text = dao.checkExits(Phone, email, account);
            if(!text.equalsIgnoreCase("accept")){           
            request.setAttribute("name1", name);
            request.setAttribute("alert", text);
            request.setAttribute("email1", email);
            request.setAttribute("phone1", Phone);
            request.setAttribute("gender1", gender);
            request.setAttribute("account1", account);
            request.setAttribute("password1", password);
            doGet(request, response);
            }
            else{
                try {
                    Account A = new Account(name,g,email,Phone,account,password,p);
                    HttpSession session = request.getSession();
                    session.setAttribute("account",A);
                    GroupDAO dao1 = new GroupDAO();
                    dao1.insert(A, p);
                    SendEmail SE = new SendEmail();
                    SE.sendEmail(2, A);
                    response.sendRedirect("thankyou.jsp");
                } catch (MessagingException ex) {
                    Logger.getLogger(LoadGroupsServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
