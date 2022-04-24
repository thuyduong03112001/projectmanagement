/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        response.sendRedirect("/workflowbox/");

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
//        processRequest(request, response);
        String account = request.getParameter("account");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember");
        AccountDAO adb = new AccountDAO();
        Account a = adb.getAcc(account, pass);
        if (a == null) {
            request.setAttribute("msgCommon", "Wrong account or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            session.setAttribute("pass", pass);
            //Remember - cookies
            Cookie cUser = new Cookie("user", account);
            Cookie cPass = new Cookie("pass", pass);
            Cookie cRemember = new Cookie("remember", remember);
            if (remember != null) {
                cUser.setMaxAge(60 * 60 * 24 * 10); // 10 ngay
                cPass.setMaxAge(60 * 60 * 24 * 10); // 10 ngay
                cRemember.setMaxAge(60 * 60 * 24 * 10); // 10 ngay
            } else { // kill all
                cUser.setMaxAge(0);
                cPass.setMaxAge(0);
                cRemember.setMaxAge(0);
            }
            response.addCookie(cUser);
            response.addCookie(cPass);
            response.addCookie(cRemember);
//            request.getRequestDispatcher("home.jsp").forward(request, response);
            response.sendRedirect("/workflowbox/");
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
