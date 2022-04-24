/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.GroupDAO;
import dal.ProjectDAO;
import dal.RequirementDAO;
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
import model.Project;
import model.Requirement;
import model.Setting;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RequirementDetailServlet", urlPatterns = {"/RequirementDetail"})
public class RequirementDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet RequirementDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequirementDetailServlet at " + request.getContextPath() + "</h1>");
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
        RequirementDAO rdb = new RequirementDAO();
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        ProjectDAO pdb = new ProjectDAO();
        AccountDAO adb = new AccountDAO();
        //Get data of Requirement
        String rrid = request.getParameter("reqId");
        int rid;
        try {
            rid = Integer.parseInt(rrid);
            request.setAttribute("action", "update");
        } catch (Exception e) {
            rid = -1;
            request.setAttribute("action", "add");
        }
        Requirement r = rdb.getRequirementById(rid);
        request.setAttribute("req", r);
        //
        //REQ type,status,projectList,owner
        List<Groups> groupList = gdb.getAll();
        List<Setting> reqTypeList = sdb.getAllByTypeId(4);
        List<Setting> reqStatusList = sdb.getAllByTypeId(3);
//        List<Project> projectList = pdb.getAllProject();
        List<Account> ownerList = adb.getAll();
        request.setAttribute("reqStatusList", reqStatusList);
        request.setAttribute("reqTypeList", reqTypeList);
//        request.setAttribute("projectList", projectList);
        request.setAttribute("ownerList", ownerList);
        request.setAttribute("groupList", groupList);
        //

        request.getRequestDispatcher("requirementdetail.jsp").forward(request, response);
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
        RequirementDAO rdb = new RequirementDAO();
        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String rpid = request.getParameter("pid");
        String rgid = request.getParameter("gid");
        String rowner = request.getParameter("owner");
        String rtypeId = request.getParameter("typeId");
        String rstatusId = request.getParameter("statusId");
        String rdetail = request.getParameter("rdetail");
        int gid, pid, typeId, statusId, owner;
        try {
            pid = Integer.parseInt(rpid);
        } catch (NumberFormatException e) {
            pid = -1;
        }
        try {
            gid = Integer.parseInt(rgid);
        } catch (NumberFormatException e) {
            gid = -1;
        }
        try {
            typeId = Integer.parseInt(rtypeId);
        } catch (NumberFormatException e) {
            typeId = -1;
        }
        try {
            statusId = Integer.parseInt(rstatusId);
        } catch (NumberFormatException e) {
            statusId = -1;
        }
        try {
            owner = Integer.parseInt(rowner);
        } catch (NumberFormatException e) {
            owner = -1;
        }
        if (action.equals("add")) {
            rdb.addGroup(title, pid, owner, statusId, typeId, gid, rdetail);
            response.sendRedirect("RequirementList");
        } else {
            int rid;
            String rrid = request.getParameter("rid");
            try {
                rid = Integer.parseInt(rrid);
            } catch (NumberFormatException e) {
                rid = -1;
            }
            rdb.updateGroup(rid, title, pid, owner, statusId, typeId, gid, rdetail);
            response.sendRedirect("RequirementDetail?reqId=" + rrid);
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
