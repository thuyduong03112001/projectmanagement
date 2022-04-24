/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.TimesheetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Timesheet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TimesheetApprove", urlPatterns = {"/timesheetApprove"})
public class TimesheetApprove extends HttpServlet {

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
            out.println("<title>Servlet TimesheetApprove</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimesheetApprove at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        String tid = request.getParameter("tid");
        HttpSession session = request.getSession();
        TimesheetDAO dao = new TimesheetDAO();
        Timesheet timesheet = new Timesheet();

        if (action.equalsIgnoreCase("approve")) {
            timesheet = dao.getById(tid);
            timesheet.setStatus("55");
            dao.updateTimesheet(timesheet);
        }


        response.sendRedirect("timesheetReview");
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
        String tid = request.getParameter("tid");
        HttpSession session = request.getSession();
        TimesheetDAO dao = new TimesheetDAO();
        Timesheet timesheet = new Timesheet();

        timesheet = dao.getById(tid);
        timesheet.setStatus("56");
        timesheet.setRejectReason(request.getParameter("rejectReason"));
        dao.updateTimesheet(timesheet);
        
        response.sendRedirect("timesheetReview");
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
