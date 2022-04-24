/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.GroupDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Groups;
import model.Setting;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GroupListServlet", urlPatterns = {"/GroupList"})
public class GroupListServlet extends HttpServlet {

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
            out.println("<title>Servlet GroupListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GroupListServlet at " + request.getContextPath() + "</h1>");
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
        //Ham nay dung de tim kiem cac ban ghi Group
        //lay du lieu
        String rpid = request.getParameter("pid");
        String value = request.getParameter("value");
        String rtypeId = request.getParameter("type");
        String rstatusId = request.getParameter("status");
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        //validate giá trị
        int pid=-1;
        try {
            pid = Integer.parseInt(rpid);
        } catch (NumberFormatException e) {
            pid=-1;
        }
        int typeId=-1;
        try {
            typeId = Integer.parseInt(rtypeId);
        } catch (NumberFormatException e) {
            typeId=-1;
        }
        int statusId=-1;
        try {
            statusId = Integer.parseInt(rstatusId);
        } catch (NumberFormatException e) {
            statusId=-1;
        }
        //tim du lieu phu hop
        List<Groups> data = gdb.searchGroup(value,pid,typeId,statusId);
        System.out.println(data.size());
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
        List<Groups> groupList = gdb.getListByPage(data, start, end);
        System.out.println(groupList.size());
        //truyen du lieu cu
        request.setAttribute("pid", pid);
        request.setAttribute("value", value);
        request.setAttribute("typeId", typeId);
        request.setAttribute("statusId", statusId);
        //truyen du lieu de paginate
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        //truyen du lieu
        List<Groups> parentGroupList = gdb.getAllParentGroup();
        List<Setting> typeGroupList = sdb.getAllGroupType();
        List<Setting> statusGroupList = sdb.getAllGroupStatus();
        request.setAttribute("groupList", groupList);
        request.setAttribute("parentGroupList", parentGroupList);
        request.setAttribute("statusGroupList", statusGroupList);
        request.setAttribute("typeGroupList", typeGroupList);
        request.getRequestDispatcher("grouplist.jsp").forward(request, response);
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
