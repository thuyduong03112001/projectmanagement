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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Groups;
import model.Project;
import model.Requirement;
import model.Setting;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RequirementListServlet", urlPatterns = {"/RequirementList"})
public class RequirementListServlet extends HttpServlet {

    //number per page
    public static final int NUMBER_PER_PAGE = 10;

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
            out.println("<title>Servlet RequirementListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequirementListServlet at " + request.getContextPath() + "</h1>");
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
        
        //Check Login
        AccountDAO adb = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        Cookie[] cookies = request.getCookies();
        String username="", pass="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    username=cookie.getValue();
                }
                if (cookie.getName().equals("pass")) {
                    pass=cookie.getValue();
                }
            }
        }
        if ((Account)session.getAttribute("account") == null && adb.checkCookie(username, pass)==null) {
            response.sendRedirect("login.jsp");
            return;
        }
        //
        //
        RequirementDAO rdb = new RequirementDAO();
        SettingDAO sdb = new SettingDAO();
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();

        String rprojectId = request.getParameter("pid");
        String rgroupId = request.getParameter("gid");
        String rownerid = request.getParameter("oid");
        String rtypeId = request.getParameter("typeId");
        String rstatusId = request.getParameter("statusId");
        String title = request.getParameter("value");
        int projectId, groupId, typeId, statusId, ownerId;
        try {
            groupId = Integer.parseInt(rgroupId);
        } catch (NumberFormatException e) {
            groupId = -1;
        }
        try {
            projectId = (groupId<0)? -1 : Integer.parseInt(rprojectId);
        } catch (NumberFormatException e) {
            projectId = -1;
        }
        try {
            ownerId = (projectId<0)? -1 : Integer.parseInt(rownerid);
        } catch (NumberFormatException e) {
            ownerId = -1;
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
        List<Requirement> data = rdb.search(adb.checkCookie(username, pass).getId(), groupId, projectId, ownerId, typeId, statusId, title);

        //paginate
        int page, numberPerPage = NUMBER_PER_PAGE; //numberPerPage: so luong Group trong 1 trang
        int size = data.size();
        int num = (size % numberPerPage == 0 ? (size / numberPerPage) : ((size / numberPerPage) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(page * numberPerPage, size);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        List<Requirement> requirementList = rdb.getListByPage(data, start, end);
        //
        //REQ type,status,projectList,owner
        List<Groups> groupList = gdb.getAllGroupHasRequirement();
        if (groupId > 0) {
            List<Project> projectList = pdb.getAllProjectHasRequirementByGroupId(groupId);
            request.setAttribute("projectList", projectList);
        }
        if (projectId > 0) {
            List<Account> ownerList = adb.getAllAccountOwnRequirementByProjectId(groupId, projectId);
            request.setAttribute("ownerList", ownerList);
        }
        List<Setting> reqTypeList = sdb.getAllByTypeId(4);
        List<Setting> reqStatusList = sdb.getAllByTypeId(3);
        request.setAttribute("reqStatusList", reqStatusList);
        request.setAttribute("reqTypeList", reqTypeList);
        request.setAttribute("groupList", groupList);
        //
        //Old data
        request.setAttribute("pid", projectId);
        request.setAttribute("gid", groupId);
        request.setAttribute("oid", ownerId);
        request.setAttribute("typeId", typeId);
        request.setAttribute("statusId", statusId);
        request.setAttribute("value", title);

        //
        request.setAttribute("requirementList", requirementList);
        request.getRequestDispatcher("requirementlist.jsp").forward(request, response);
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
        SettingDAO sdb = new SettingDAO();
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();
        AccountDAO adb = new AccountDAO();

        String rprojectId = request.getParameter("pid");
        String rgroupId = request.getParameter("gid");
        String rownerid = request.getParameter("oid");
        String rtypeId = request.getParameter("typeId");
        String rstatusId = request.getParameter("statusId");
        String title = request.getParameter("value");
        int projectId, groupId, typeId, statusId, ownerId;
        try {
            projectId = Integer.parseInt(rprojectId);
        } catch (NumberFormatException e) {
            projectId = -1;
        }
        try {
            groupId = Integer.parseInt(rgroupId);
        } catch (NumberFormatException e) {
            groupId = -1;
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
            ownerId = Integer.parseInt(rownerid);
        } catch (NumberFormatException e) {
            ownerId = -1;
        }
        List<Requirement> data = rdb.search(18, groupId, projectId, ownerId, typeId, statusId, title);
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
