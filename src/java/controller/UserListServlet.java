/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DELL
 */
public class UserListServlet extends HttpServlet {

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
            out.println("<title>Servlet UserListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListServlet at " + request.getContextPath() + "</h1>");
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
        Account acc = (Account) session.getAttribute("account");
        AccountDAO adb = new AccountDAO();
        String groupcode = request.getParameter("groupcode");
        List<Account> list1 = adb.FilterByGender();       
        List<Account> list2 = adb.FilterByGroupCode();
        List<Account> list3 = adb.FilterByStatus();
        if (acc.getRoleId() == 6) {
            List<Account> list = adb.getHRAllocation(groupcode);
            request.setAttribute("list", list);
        }
        if (acc.getRoleId() == 5) {
            List<Account> listA = adb.getAll1();
            request.setAttribute("list", listA);
        }
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
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
        AccountDAO adb = new AccountDAO();
        String genderOfvalue = request.getParameter("genderOfvalue");
        String groupCode = request.getParameter("groupCode");
        String StatusOfAccount = request.getParameter("StatusOfAccount");
        List<Account> listA = adb.userList(genderOfvalue, groupCode, StatusOfAccount);
        List<Account> list1 = adb.FilterByGender();       
        List<Account> list2 = adb.FilterByGroupCode();
        List<Account> list3 = adb.FilterByStatus();
        
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);       
        request.setAttribute("genderOfvalue", genderOfvalue);       
        request.setAttribute("StatusOfAccount", StatusOfAccount);
        request.setAttribute("groupCode", groupCode);
        
        request.setAttribute("list", listA);      
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
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
