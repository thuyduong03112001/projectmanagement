/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.RequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RequirementChanges;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RequirementChangeSevlet", urlPatterns = {"/RequirementChange"})
public class RequirementChangeSevlet extends HttpServlet {

    public static final int NUMBER_PER_PAGE = 10;
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
            out.println("<title>Servlet RequirementChangeSevlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequirementChangeSevlet at " + request.getContextPath() + "</h1>");
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
        RequirementDAO rdb = new RequirementDAO();
        String rrid = request.getParameter("rid");
        int rid = 0;
        try {
            rid = Integer.parseInt(rrid);
        } catch (Exception e) {
        }
        List<RequirementChanges> data = rdb.getAllRequirementChangesByRequirementId(rid);
        
        //paginate
        int page, numberPerPage = NUMBER_PER_PAGE; //numberPerPage: so luong Group trong 1 trang
        int size = data.size();
        int num = (size % numberPerPage == 0 ? (size / numberPerPage) : ((size / numberPerPage) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(page * numberPerPage, size);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        List<RequirementChanges> list = rdb.getListByPage2(data, start, end);
        
        //forward
        request.setAttribute("rid", rid);
        request.setAttribute("data", list);
        request.getRequestDispatcher("requestchangeslist.jsp").forward(request, response);
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
        processRequest(request, response);
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
