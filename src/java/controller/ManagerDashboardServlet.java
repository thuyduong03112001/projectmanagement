/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DashDAO;
import dal.ProjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Dash;
import model.Issue;
import model.NewIssue;
import model.Project;
import model.Risk;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManagerDashboardServlet", urlPatterns = {"/dashboard"})
public class ManagerDashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerDashboardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerDashboardServlet at " + request.getContextPath() + "</h1>");
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
//        Account acc = new Account();
//        acc.setId(1);
        if (acc == null || acc.getRoleId() != 6) {
//        if (acc == null) {    
            response.sendRedirect("/workflowbox/");
            return;
        }
        // graph
        int projectTime = -1;
        int statusTime = 1;
        projectTime = request.getParameter("projectTime") == null ? -1 : Integer.parseInt(request.getParameter("projectTime"));
        statusTime = request.getParameter("statusTime") == null ? 1 : Integer.parseInt(request.getParameter("statusTime"));

        int projectIssues = -1;
        int statusIssues = 1;
        projectIssues = request.getParameter("projectIssues") == null ? -1 : Integer.parseInt(request.getParameter("projectIssues"));
        statusIssues = request.getParameter("statusIssues") == null ? 1 : Integer.parseInt(request.getParameter("statusIssues"));

        DashDAO db = new DashDAO();
        List<Dash> timeList = db.getTimesheetReportManager(acc.getId(), projectTime, statusTime);
        request.setAttribute("managerId", acc.getId());
        request.setAttribute("timeList", timeList);
        request.setAttribute("projectTime", projectTime);
        request.setAttribute("statusTime", statusTime);

        List<Dash> issuesList = db.getIssuesReport(acc.getId(), projectIssues, statusIssues);
        request.setAttribute("issuesList", issuesList);
        request.setAttribute("projectIssues", projectTime);
        request.setAttribute("statusIssues", statusIssues);
        // graph
        
        // list
        List<NewIssue> tableIssues = db.getLastIssuesByManager(acc.getId());
        request.setAttribute("tableIssues", tableIssues);
        List<NewIssue> tableRisks = db.getLastRisksByManager(acc.getId());
        request.setAttribute("tableRisks", tableRisks);
        request.getRequestDispatcher("ManagerDashboard.jsp").forward(request, response);
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
        doGet(request, response);
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
