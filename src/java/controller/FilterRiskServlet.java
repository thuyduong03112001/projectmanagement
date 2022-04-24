/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.RiskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Risk;

/**
 *
 * @author HP
 */
@WebServlet(name = "FilterRiskServlet", urlPatterns = {"/filterR"})
public class FilterRiskServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterRiskServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterRiskServlet at " + request.getContextPath() + "</h1>");
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
            String projectName = request.getParameter("projectName");
            String createdBy = request.getParameter("createdBy");
            String category = request.getParameter("category");
            String owner = request.getParameter("owner");
            String updatedFrom = request.getParameter("updatedFrom");
            String updatedTo = request.getParameter("updatedTo");
            String status = request.getParameter("status");
            RiskDAO dao = new RiskDAO();
        try {
            List<Risk> list = dao.getbyFilter(projectName, createdBy, category, status, owner, updatedFrom,updatedTo);
            List<Risk> list1 = dao.getAll();
            request.setAttribute("listR", list);
            request.setAttribute("listS", list1);
            request.setAttribute("pN", projectName);
            request.setAttribute("cB", createdBy);
            request.setAttribute("cG", category);
            request.setAttribute("oN", owner);
            request.setAttribute("uF", updatedFrom);
            request.setAttribute("uT", updatedTo);
            request.setAttribute("sT", status);
            request.getRequestDispatcher("risk.jsp").forward(request, response);
        } catch (ParseException ex) {
            PrintWriter out = response.getWriter();
            out.print(ex);
        }
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
