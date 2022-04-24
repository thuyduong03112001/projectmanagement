/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SettingListServlet", urlPatterns = {"/SettingList"})
public class SettingListServlet extends HttpServlet {

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
            out.println("<title>Servlet SettingListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingListServlet at " + request.getContextPath() + "</h1>");
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
        String value = request.getParameter("value");
        String rtype = request.getParameter("type");
        String rstatus = request.getParameter("status");
        String sortby = request.getParameter("sortby");
        int type = -1, status = -1;
        try {
            type = Integer.parseInt(rtype);
        } catch (NumberFormatException e) {
            type = -1;
        }
        try {
            status = Integer.parseInt(rstatus);
        } catch (NumberFormatException e) {
            status = -1;
        }
        
        SettingDAO sdb = new SettingDAO();
        List<Setting> data = sdb.getAllWithCondition(-1, type, value, status, sortby);
        //paginate
        int page, numberPerPage = 10; //numberPerPage: so luong Group trong 1 trang
        int size = data.size();
        int num = (size%numberPerPage==0?(size/numberPerPage):((size/numberPerPage)+1));
        String xpage=request.getParameter("page");
        if(xpage==null){
            page=1;
        }else{
            page=Integer.parseInt(xpage);
        }
        int start, end;
        start=(page-1)*numberPerPage;
        end=Math.min(page*numberPerPage, size);
        List<Setting> settingList = sdb.getListByPage(data, start, end);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        //tra lai du lieu cu
        request.setAttribute("value", value);
        request.setAttribute("type", type);
        request.setAttribute("status", status);
        request.setAttribute("sortby", sortby);
        //truyen du lieu
        List<Type> typeSettingList = sdb.getAllType();
        request.setAttribute("settingList", settingList);
        request.setAttribute("typeSettingList", typeSettingList);
        request.getRequestDispatcher("settingList.jsp").forward(request, response);
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
        request.getRequestDispatcher("settingList.jsp").forward(request, response);
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
