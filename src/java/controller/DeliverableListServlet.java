/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DeliverableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Deliverable;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeliverableListServlet", urlPatterns = {"/deliverablelist"})
public class DeliverableListServlet extends HttpServlet {

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
            out.println("<title>Servlet SliderListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderListServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null && (acc.getRoleId() == 6 || acc.getRoleId() == 7 || acc.getRoleId() == 5)) {
        } else {
            response.sendRedirect("/workflowbox/");
            return;
        }
        //setup default
        int status = -1;
        String name = "";
        int group = -1;
        int project = -1;
        String from = "";
        String to = "";
//        HttpSession session = request.getSession();
        DeliverableDAO pdb = new DeliverableDAO();
        List<Deliverable> list;

        //already have
        if (session.getAttribute("status2") != null) {
            status = (int) session.getAttribute("status3");
            name = (String) session.getAttribute("name3");
            group = (int) session.getAttribute("group3");
            from = (String) session.getAttribute("from3");
            to = (String) session.getAttribute("to3");
        }
        int staff;
        switch (acc.getRoleId()) {
            case 7:
                staff = 1;
                break;
            case 6:
                staff = 0;
                break;
            default:
                staff = -1;
                break;
        }
        list = pdb.filter(name, group, project, status, from, to, acc.getId(), staff);

        //pagination
        int page, numperpage = 3;
        int num;
        if (list.isEmpty()) {
            num = 1;
            request.setAttribute("nothing", "nothing img");
        } else {
            num = (list.size() % numperpage == 0
                    ? (list.size() / numperpage) : ((list.size() / numperpage) + 1)); //number of pages
        }
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        page = page <= 1 ? 1 : page;
        page = page >= num ? num : page;

        int start, end;
        int size = list.size();
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Deliverable> listOut = pdb.getListByPage(list, start, end);

        session.setAttribute("status3", status);
        session.setAttribute("page", page);
        session.setAttribute("name3", name);
        session.setAttribute("group3", group);
        session.setAttribute("project3", project);
        session.setAttribute("from3", from);
        session.setAttribute("to3", to);
        session.setAttribute("num3", num);
        session.setAttribute("list3", listOut);
        request.getRequestDispatcher("projectdeliverable.jsp").forward(request, response);
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
//        processRequest(request, response);
        HttpSession session = request.getSession();
        DeliverableDAO pdb = new DeliverableDAO();
        int status = Integer.parseInt(request.getParameter("status3"));
        String name = request.getParameter("name3");
        int group = Integer.parseInt(request.getParameter("group3"));
        int project = Integer.parseInt(request.getParameter("project3"));
        String from = request.getParameter("from3");
        String to = request.getParameter("to3");
        Account acc = (Account) session.getAttribute("account");
        int staff;
        switch (acc.getRoleId()) {
            case 7:
                staff = 1;
                break;
            case 6:
                staff = 0;
                break;
            default:
                staff = -1;
                break;
        }
        List<Deliverable> list = pdb.filter(name, group, project, status, from, to, acc.getId(), staff);
        //pagination
        int page, numperpage = 3;
        int num;
        if (list.isEmpty()) {
            num = 1;
            request.setAttribute("nothing", "nothing img");
        } else {
            num = (list.size() % numperpage == 0
                    ? (list.size() / numperpage) : ((list.size() / numperpage) + 1)); //number of pages
        }
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        page = page <= 1 ? 1 : page;
        page = page >= num ? num : page;

        int start, end;
        int size = list.size();
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Deliverable> listOut = pdb.getListByPage(list, start, end);

        session.setAttribute("status3", status);
        session.setAttribute("page", page);
        session.setAttribute("name3", name);
        session.setAttribute("group3", group);
         session.setAttribute("project3", project);
        session.setAttribute("from3", from);
        session.setAttribute("to3", to);
        session.setAttribute("num3", num);
        session.setAttribute("list3", listOut);
        request.getRequestDispatcher("projectdeliverable.jsp").forward(request, response);
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
