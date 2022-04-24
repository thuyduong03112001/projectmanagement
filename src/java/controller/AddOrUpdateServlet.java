/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.GroupDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Groups;
import model.Setting;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddOrUpdateServlet", urlPatterns = {"/AddOrUpdate"})
public class AddOrUpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet AddOrUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddOrUpdateServlet at " + request.getContextPath() + "</h1>");
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
        GroupDAO gdb = new GroupDAO();
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        List<Account> accList = adb.getAll();
        List<Groups> groupList = gdb.getAll();
        List<Setting> typeGroupList = sdb.getAllGroupType();
        List<Setting> statusGroupList = sdb.getAllGroupStatus();
        System.out.println("??????????");
        int gid;
        try {
            gid = Integer.parseInt(request.getParameter("groupId"));
        } catch (NumberFormatException e) {
            gid = -1;
        }

        request.setAttribute("group", gdb.getGroupById(gid));
        if (gid > 0) {
            request.setAttribute("action", "update");
        } else {
            request.setAttribute("action", "add");
        }

        request.setAttribute("accList", accList);
        request.setAttribute("groupList", groupList);
        request.setAttribute("statusGroupList", statusGroupList);
        request.setAttribute("typeGroupList", typeGroupList);
        request.getRequestDispatcher("groupdetail.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String rpid = request.getParameter("pid");
        String rstatus = request.getParameter("status");
        String des = request.getParameter("des");
        String rtype = request.getParameter("type");
        String[] rmid = request.getParameterValues("mid");
        
        int pid;
        try {
            pid = Integer.parseInt(rpid);
        } catch (NumberFormatException e) {
            pid = -1;
        }
        int type = -1;
        try {
            type = Integer.parseInt(rtype);
        } catch (NumberFormatException e) {
            type = -1;
        }
        int status = -1;
        try {
            status = Integer.parseInt(rstatus);
        } catch (NumberFormatException e) {
            status = -1;
        }

        GroupDAO gdb = new GroupDAO();

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("Update")) {
            String rid = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(rid);
            } catch (NumberFormatException e) {
                id = -1;
            }
            gdb.updateGroup(id, name, pid, type, status, des, rmid);
        } else {
            gdb.addGroup(name, pid, type, status, des, rmid);
        }
        response.sendRedirect("GroupList");
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
