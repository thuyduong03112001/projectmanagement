/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.IssueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.NewIssue;

/**
 *
 * @author DELL
 */
public class IssueListServlet extends HttpServlet {

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
            out.println("<title>Servlet IssueListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IssueListServlet at " + request.getContextPath() + "</h1>");
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
        IssueDAO dao = new IssueDAO();
        List<NewIssue> list1 = dao.FilterByType();
        List<NewIssue> list2 = dao.FilterByProjectName();
        List<NewIssue> list3 = dao.FilterByCreatedDate();
        List<NewIssue> list4 = dao.FilterByCreatedBy();
        List<NewIssue> list5 = dao.FilterByIncharge();
        List<NewIssue> list6 = dao.FilterByStatus();
        List<NewIssue> list = dao.getAll();  
        int page, numperpage=5;
        int size = list.size();
        int num=(size%5==0?(size/5):((size/5))+1);
        String xpage= request.getParameter("page");
        if(xpage==null){
            page=1;
        }else{
            page=Integer.parseInt(xpage);
        }
        int start, end;
        start=(page-1)*numperpage;
        end=Math.min(page*numperpage, size);
        List<NewIssue> list7 = dao.getListByPage((ArrayList<NewIssue>) list, start, end);
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);
        request.setAttribute("list4", list4);
        request.setAttribute("list5", list5);
        request.setAttribute("list6", list6);
        request.setAttribute("list", list);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("list", list7);
        request.getRequestDispatcher("issueslist.jsp").forward(request, response);
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
        IssueDAO dao = new IssueDAO();
        String title = request.getParameter("title");
        if (title == null) {
            title = "";
        }
        String itype = request.getParameter("itype");
        String projectName = request.getParameter("projectName");
        String CreatedDate = request.getParameter("CreatedDate");
        String CreatedBy1 = request.getParameter("CreatedBy1");
        String Incharge1 = request.getParameter("Incharge1");
        String istatus = request.getParameter("istatus");
        List<NewIssue> list1 = dao.FilterByType();
        List<NewIssue> list2 = dao.FilterByProjectName();
        List<NewIssue> list3 = dao.FilterByCreatedDate();
        List<NewIssue> list4 = dao.FilterByCreatedBy();
        List<NewIssue> list5 = dao.FilterByIncharge();
        List<NewIssue> list6 = dao.FilterByStatus();
        List<NewIssue> list7 = dao.filter(itype, projectName, CreatedDate, CreatedBy1, Incharge1, istatus);
        List<NewIssue> listI = dao.searchByTitle(title);
        int page, numperpage=5;
        int size = list7.size();
        int num=(size%5==0?(size/5):((size/5))+1);
        String xpage= request.getParameter("page");
        if(xpage==null){
            page=1;
        }else{
            page=Integer.parseInt(xpage);
        }
        int start, end;
        start=(page-1)*numperpage;
        end=Math.min(page*numperpage, size);
        List<NewIssue> list8 = dao.getListByPage((ArrayList<NewIssue>) list7, start, end);
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list3", list3);
        request.setAttribute("list4", list4);
        request.setAttribute("list5", list5);
        request.setAttribute("list6", list6);
        request.setAttribute("title", title);
        request.setAttribute("list", listI);
        request.setAttribute("list", list7);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("list", list8);
        request.getRequestDispatcher("issueslist.jsp").forward(request, response);

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
