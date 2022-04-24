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
import java.util.ArrayList;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account A = (Account) session.getAttribute("A");
        AccountDAO dao = new AccountDAO();
        dao.insert(A);
        response.sendRedirect("login.jsp");
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("phone");
        String gender = request.getParameter("gender");
        int sex;
        String type;
        if (gender == "1") {
            sex = 12;
            type = "Female";
        } else {
            sex = 13;
            type = "Male";
        }
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Encrypt E = new Encrypt();
        String epass="";
        try {
            epass = toHexString(E.Encrypt(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String repassword = request.getParameter("confirm");
        AccountDAO dao = new AccountDAO();
        String res = dao.checkExits(mobile, email, account);
        if (!res.equalsIgnoreCase("Accept")) {
            request.setAttribute("res", res);
            request.setAttribute("name1", fullname);
            request.setAttribute("email1", email);
            request.setAttribute("phone1", mobile);
            request.setAttribute("gender1", gender);
            request.setAttribute("account1", account);
            request.setAttribute("password1", password);
            request.setAttribute("confirm1", repassword);
            request.getRequestDispatcher("sign-up.jsp").forward(request, response);
        } else {
            try {
               // out.print(account+password+email+mobile+fullname);
                SendEmail SE = new SendEmail();
                Account A = new Account(fullname, email, mobile, sex, account, epass);
                SE.sendEmail(1, A);
                HttpSession session = request.getSession();
                session.setAttribute("A", A);
                session.setAttribute("acc", account);
                session.setAttribute("pass", password);
                session.setAttribute("email", email);
                session.setAttribute("phone", mobile);
                session.setAttribute("name", fullname);
                session.setAttribute("gender", type);
                response.sendRedirect("thankyou.jsp");
            } catch (IOException | MessagingException e) {
                out.print(e);
            }
        }
    }
    //response.sendRedirect("result.jsp");

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
