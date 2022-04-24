/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PostDAO;
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
import model.Post;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PostListServlet", urlPatterns = {"/postlist"})
public class PostListServlet extends HttpServlet {

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
            out.println("<title>Servlet PostListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostListServlet at " + request.getContextPath() + "</h1>");
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
        if(acc==null || acc.getRoleId()!=5) {
            response.sendRedirect("/workflowbox/");
            return;
        }
        //setup default
        int status = -1;
        String sort = "";
        String title = "";
        String author = "";
        List<Integer> categoryId = new ArrayList<>();
//        HttpSession session = request.getSession();
        PostDAO pdb = new PostDAO();
        List<Post> list;
        
         //already have
        if (session.getAttribute("status1")!=null) {
            status = (int) session.getAttribute("status1");
            sort = (String) session.getAttribute("sort1");
            title = (String) session.getAttribute("title1");
            author = (String) session.getAttribute("author1");
            categoryId = (List<Integer>) session.getAttribute("category1");
            list = pdb.filter(title, categoryId, "", author, status, sort);
        } else {
            list = pdb.getAll();
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
        List<Post> listOut = pdb.getListByPage(list, start, end);

        session.setAttribute("sort1", sort);
        session.setAttribute("category1", categoryId);
        session.setAttribute("status1", status);
        session.setAttribute("page", page);
        session.setAttribute("title1", title);
        session.setAttribute("author1", author);
        session.setAttribute("num1", num);
        session.setAttribute("list1", listOut);
        request.getRequestDispatcher("postslist.jsp").forward(request, response);
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
        Account acc = (Account) session.getAttribute("account");
        if(acc==null || acc.getRoleId()!=5) {
            response.sendRedirect("/workflowbox/");
            return;
        }
        String status = request.getParameter("status1");
        int statusId;
        try {
            statusId = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            statusId = -1;
        }

        //category
        String[] category = request.getParameterValues("category1");
        List<Integer> categoryId = new ArrayList<>();

        if (category != null) {
            for (String category1 : category) {
                categoryId.add(Integer.parseInt(category1));
            }
        }

        //title, author, sort
        String title = request.getParameter("title1");
        String author = request.getParameter("author1");
        String sort = request.getParameter("sort1");
        
        //filter
        PostDAO pdb = new PostDAO();
        List<Post> listFiltered = pdb.filter(title, categoryId, "", author, statusId, sort);

        //pagination
        int page, numperpage = 3;
        int num;
        if (listFiltered.isEmpty()) {
            num = 1;
            request.setAttribute("nothing", "nothing img");
        } else {
            num = (listFiltered.size() % numperpage == 0
                ? (listFiltered.size() / numperpage) : ((listFiltered.size() / numperpage) + 1)); //number of pages
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
        int size = listFiltered.size();
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Post> list = pdb.getListByPage(listFiltered, start, end);

//        HttpSession session = request.getSession();
        session.setAttribute("sort1", sort);
        session.setAttribute("category1", categoryId);
        session.setAttribute("status1", statusId);
        session.setAttribute("page", page);
        session.setAttribute("title1", title);
        session.setAttribute("author1", author);
        session.setAttribute("num1", num);
        session.setAttribute("list1", list);
        request.getRequestDispatcher("postslist.jsp").forward(request, response);
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
