/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.AccountDAO;
import dal.ProjectDAO;
import dal.SettingDAO;
import dal.TimesheetDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Project;
import model.Setting;
import model.Timesheet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TimesheetServlet", urlPatterns = {"/timesheet"})
public class TimesheetServlet extends HttpServlet {

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
            out.println("<title>Servlet TimesheetServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimesheetServlet at " + request.getContextPath() + "</h1>");
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
        ProjectDAO projectDao = new ProjectDAO();
        SettingDAO settingDao = new SettingDAO();
        TimesheetDAO timesheetDao = new TimesheetDAO();

        Account acc = (Account) session.getAttribute("account");
        
        List<Timesheet> listT = timesheetDao.getByAcc(acc, null, null, null, null);
        List<Setting> ListProcess = settingDao.getAllByTypeId(7);
        List<Project> listProject = projectDao.getAllByStaff(acc.getId());
        String role = timesheetDao.getPrjRole(acc.getId());
        
        session.setAttribute("projectRole", role);
        session.setAttribute("ListProcess", ListProcess);
        session.setAttribute("listProject", listProject);
        session.setAttribute("listT", listT);
        request.getRequestDispatcher("Timesheet.jsp").forward(request, response);
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
        TimesheetDAO timesheetDao = new TimesheetDAO();
        
        Account acc = (Account) session.getAttribute("account");
        
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String process = request.getParameter("process");
        String project = request.getParameter("project");
        List<Timesheet> listT = timesheetDao.getByAcc(acc, project, start, end, process);
        
//        String test = start + " " + end;
//        request.setAttribute("test", test);
        session.setAttribute("listT", listT);
        request.getRequestDispatcher("Timesheet.jsp").forward(request, response);
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
