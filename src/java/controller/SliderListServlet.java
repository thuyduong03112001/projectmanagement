/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.SliderDAO;
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
import model.Slider;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SliderListServlet", urlPatterns = {"/sliderlist"})
public class SliderListServlet extends HttpServlet {

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
        if (acc == null || acc.getRoleId() != 5) {
            response.sendRedirect("/workflowbox/");
            return;
        }
        //setup default
        int status = 31;
        String search = "";
//        HttpSession session = request.getSession();
        SliderDAO pdb = new SliderDAO();
        List<Slider> list;

        //already have
        if (session.getAttribute("status2") != null) {
            status = (int) session.getAttribute("status2");
            search = (String) session.getAttribute("search2");
            list = pdb.filter(search, status);
        } else {
            list = pdb.filter(search, status);
        }

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
        List<Slider> listOut = pdb.getListByPage(list, start, end);

        session.setAttribute("status2", status);
        session.setAttribute("page", page);
        session.setAttribute("search2", search);
        session.setAttribute("num2", num);
        session.setAttribute("list2", listOut);
        request.getRequestDispatcher("sliderlist.jsp").forward(request, response);
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
        SliderDAO pdb = new SliderDAO();
        int status = Integer.parseInt(request.getParameter("status2"));
        String search = request.getParameter("search2");
        List<Slider> list = pdb.filter(search, status);
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
        List<Slider> listOut = pdb.getListByPage(list, start, end);

        session.setAttribute("status2", status);
        session.setAttribute("page", page);
        session.setAttribute("search2", search);
        session.setAttribute("num2", num);
        session.setAttribute("list2", listOut);
        request.getRequestDispatcher("sliderlist.jsp").forward(request, response);
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
