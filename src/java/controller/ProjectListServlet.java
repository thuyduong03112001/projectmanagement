/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.GroupDAO;
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
import model.Project;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProjectListServlet", urlPatterns = {"/projectList"})
public class ProjectListServlet extends HttpServlet {

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
            out.println("<title>Servlet ProjectListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjectListServlet at " + request.getContextPath() + "</h1>");
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
//        acc.setId(2);
        ProjectDAO projectDao = new ProjectDAO();
        GroupDAO groupDao = new GroupDAO();
        List<Project> listP = projectDao.getAllByManager(acc.getId(),"","");
        if(acc.getRoleId()== 5){
            listP = projectDao.getAllProject(acc.getId()+"","","");
        }
        List<String> listgroup = new ArrayList<String>();
        
        session.setAttribute("listP", listP);
        request.getRequestDispatcher("projectList.jsp").forward(request, response);
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
        ProjectDAO projectDao = new ProjectDAO();
        String groupid = request.getParameter("groupId");
        String prjMan = request.getParameter("projectmanager");
        if(prjMan == null || prjMan.equals("")){
            Account acc = (Account) session.getAttribute("account");
            prjMan = acc.getId() + "";
        }
        String status = request.getParameter("status");
        List<Project> listP = projectDao.getAllByManager(Integer.parseInt(prjMan),groupid,status);
        session.setAttribute("listP", listP);
        request.getRequestDispatcher("projectList.jsp").forward(request, response);
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
