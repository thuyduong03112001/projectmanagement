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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Timesheet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TimesheetAddServlet", urlPatterns = {"/timesheetadd"})
public class TimesheetAddServlet extends HttpServlet {

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
            out.println("<title>Servlet TimesheetAddServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimesheetAddServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("TimesheetAdd.jsp").forward(request, response);
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
        Timesheet timesheet = new Timesheet();
        Account acc = (Account) session.getAttribute("account");
        
        java.sql.Date form = new java.sql.Date(new Date().getTime());
        timesheet.setDate(form.valueOf(request.getParameter("date")));
        timesheet.setAcc(acc);
        timesheet.setTitle(request.getParameter("title"));
        timesheet.setProject(projectDao.getProjectById(Integer.parseInt(request.getParameter("project"))));
        timesheet.setProcess(request.getParameter("process"));
        timesheet.setStatus("54");
        timesheet.setDuration(Double.parseDouble(request.getParameter("duration")));
        timesheet.setWorkResult(request.getParameter("workResult"));
        dao.add(timesheet);
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
