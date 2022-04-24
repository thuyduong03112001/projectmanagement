/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProjectDAO;
import dal.TimesheetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TimesheetEdit", urlPatterns = {"/TimesheetEdit"})
public class TimesheetEditServlet extends HttpServlet {

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
            out.println("<title>Servlet TimesheetEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimesheetEdit at " + request.getContextPath() + "</h1>");
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
        TimesheetDAO dao = new TimesheetDAO();

        Timesheet timesheet = dao.getById(request.getParameter("id"));
        session.setAttribute("timesheet", timesheet);
        request.getRequestDispatcher("TimesheetEdit.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        TimesheetDAO dao = new TimesheetDAO();
        ProjectDAO projectDao = new ProjectDAO();
        Timesheet timesheet = (Timesheet) session.getAttribute("timesheet");
        if (!"Approved".equals(timesheet.getStatus())) {
            java.sql.Date form = new java.sql.Date(new Date().getTime());
            timesheet.setDate(form.valueOf(request.getParameter("date")));
            timesheet.setStatus("54");
            timesheet.setTitle(request.getParameter("title"));
            timesheet.setProject(projectDao.getProjectById(Integer.parseInt(request.getParameter("project"))));
            timesheet.setProcess(request.getParameter("process"));
            timesheet.setDuration(Double.parseDouble(request.getParameter("duration")));
            timesheet.setWorkResult(request.getParameter("workResult"));
            dao.updateTimesheet(timesheet);
        }

        response.sendRedirect("timesheet");
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
