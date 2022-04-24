/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.SettingDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Setting;
import dal.SettingDAO;
import java.util.List;
import model.Type;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SettingDetailServlet", urlPatterns = {"/settingDetail"})
public class SettingDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet SettingDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingDetailServlet at " + request.getContextPath() + "</h1>");
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

        SettingDetailDAO dao = new SettingDetailDAO();
        HttpSession session = request.getSession();
        SettingDAO sldao = new SettingDAO();
//        String id = request.getParameter("id");
        String id = (String) request.getParameter("settingid");
        session.setAttribute("settingId", id);
        
        Setting setting = dao.findSetting(id);
        request.setAttribute("setting", setting);
        
        dao.getTypeById(setting.getType()).getId();
        
        List<Type> typeSettingList = sldao.getAllType();
        request.setAttribute("typeSettingList", typeSettingList);

        request.getRequestDispatcher("newjsp.jsp").forward(request, response);
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
        SettingDetailDAO dao = new SettingDetailDAO();
        HttpSession session = request.getSession();

        String id = (String) session.getAttribute("settingId");
        String typeId = request.getParameter("type");
        String value = request.getParameter("value");
        String order = request.getParameter("order");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        dao.editSetting(id, typeId, value, order, description, status);
        response.sendRedirect("settinglist");
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