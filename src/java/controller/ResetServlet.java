/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
import model.Encrypt;
import static model.Encrypt.toHexString;
import model.SendEmail;

/**
 *
 * @author HP
 */
@WebServlet(name = "Reset", urlPatterns = {"/reset"})
public class ResetServlet extends HttpServlet {

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
            out.println("<title>Servlet Reset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reset at " + request.getContextPath() + "</h1>");
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
     * @throws IOException if an
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAll();
        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String alert = dao.check1(user, email);
        boolean check = dao.check(user, email);
            if (check) {
                SendEmail SE = new SendEmail();
                Account A = dao.getAccount(email,user);
                HttpSession session = request.getSession();
                session.setAttribute("account", A);
                try {
                    SE.sendEmail(2, A);
                    request.getRequestDispatcher("thankyou.jsp").forward(request, response);
                } catch (MessagingException ex) {
                }
            }
             else {
                request.setAttribute("A", alert);
                request.getRequestDispatcher("reset.jsp").forward(request, response);
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
            PrintWriter out = response.getWriter();
            String pass = request.getParameter("password");
            Encrypt E = new Encrypt();
            String epass="";
            try {
                epass = toHexString(E.Encrypt(pass));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ResetServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String repass = request.getParameter("repassword");
            if(!pass.equalsIgnoreCase(repass)){
                String text ="Password did not match";
                request.setAttribute("text",text);
                request.getRequestDispatcher("reset1.jsp").forward(request, response);
            }
            else{
            HttpSession session = request.getSession();
            Account A = (Account)session.getAttribute("account");
            AccountDAO dao = new AccountDAO();
            dao.changePass(epass, A.getAccount(), A.getEmail());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
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
