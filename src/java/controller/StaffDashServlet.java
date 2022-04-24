/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DashDAO;
import dal.TimesheetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Dash;
import model.NewIssue;
import model.Timesheet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "StaffDashServlet", urlPatterns = {"/staffdash"})
public class StaffDashServlet extends HttpServlet {

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
            out.println("<title>Servlet StaffDashServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffDashServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null || acc.getRoleId() != 7) {
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
        List<Dash> timeList = db.getTimesheetReport(acc.getId(), projectTime, statusTime);
        request.setAttribute("staffid", acc.getId());
        request.setAttribute("timeList", timeList);
        request.setAttribute("projectTime", projectTime);
        request.setAttribute("statusTime", statusTime);

        List<Dash> issuesList = db.getIssuesReport(acc.getId(), projectIssues, statusIssues);
        request.setAttribute("issuesList", issuesList);
        request.setAttribute("projectIssues", projectIssues);
        request.setAttribute("statusIssues", statusIssues);
        // graph
        
        // list
        List<NewIssue> tableIssues = db.getLastIssues(acc.getId());
        request.setAttribute("tableIssues", tableIssues);
        List<NewIssue> tableRisks = db.getLastRisks(acc.getId());
        request.setAttribute("tableRisks", tableRisks);
        request.getRequestDispatcher("staffdash.jsp").forward(request, response);
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
