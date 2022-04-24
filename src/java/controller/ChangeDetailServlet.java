/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.RequirementDAO;
import dal.SettingDAO;
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
import model.Requirement;
import model.RequirementChanges;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangeDetailServlet", urlPatterns = {"/ChangeDetail"})
public class ChangeDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangeDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeDetailServlet at " + request.getContextPath() + "</h1>");
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
        RequirementDAO rdb = new RequirementDAO();
        SettingDAO sdb = new SettingDAO();
        String rrcid = request.getParameter("rcid");
        String rrid = request.getParameter("rid");
        int rcid = -1, rid = -1;
        try {
            rcid = Integer.parseInt(rrcid);
        } catch (Exception e) {
        }
        try {
            rid = Integer.parseInt(rrid);
        } catch (Exception e) {
        }
        RequirementChanges r = rdb.getRequirementChangesByRequirementChangeId(rcid);
        request.setAttribute("reqChange", r);
        request.setAttribute("reqTypeList", sdb.getAllByTypeId(19));
        if(r!=null){
            rid = r.getRequirementId();
        }
        request.setAttribute("accList", adb.getAllAccountCanAccessRequirement(rid));
        request.setAttribute("reqId", rid);
        request.setAttribute("myId", adb.checkCookie(username, pass).getId());
        if (rcid > 0) {
            request.setAttribute("action", "update");
        }

        request.getRequestDispatcher("changedetail.jsp").forward(request, response);
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
        String rrid = request.getParameter("rid");
        String title = request.getParameter("title");
        String rtypeId = request.getParameter("typeId");
        String detail = request.getParameter("detail");
        String version = request.getParameter("version");
        String reid = request.getParameter("eid");
        int rid = -1;
        try {
            rid = Integer.parseInt(rrid);
        } catch (Exception e) {
        }
        int typeId = -1;
        try {
            typeId = Integer.parseInt(rtypeId);
        } catch (Exception e) {
        }
        int eid = -1;
        try {
            eid = Integer.parseInt(reid);
        } catch (Exception e) {
        }
        if (request.getParameter("action").equals("update")) {
            int rcid = -1;
            String rrcid = request.getParameter("rcid");
            try {
                rcid = Integer.parseInt(rrcid);
            } catch (Exception e) {
            }
            rdb.updateRequirementChange(rcid, title, typeId, detail, version, eid);
        } else {
            rdb.addRequirementChange(rid, title, typeId, detail, version, eid);
        }
        response.sendRedirect("RequirementChange?rid="+rrid);
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
