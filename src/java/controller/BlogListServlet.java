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
import model.Post;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BlogListServlet", urlPatterns = {"/bloglist"})
public class BlogListServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogListServlet at " + request.getContextPath() + "</h1>");
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
        //setup default
        String title = "";
        String brief = "";
        List<Integer> categoryId = new ArrayList<>();
        HttpSession session = request.getSession();
        PostDAO pdb = new PostDAO();
        List<Post> list;
        
         //already have
        if (session.getAttribute("flag")!=null) {
            title = (String) session.getAttribute("title");
            brief = (String) session.getAttribute("brief");
            categoryId = (List<Integer>) session.getAttribute("category");
            list = pdb.filter(title, categoryId, brief, "", 3, "");
        } else {
            list = pdb.getAllSlide();
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

        
        session.setAttribute("category", categoryId);
        session.setAttribute("title", title);
        session.setAttribute("page", page);
        session.setAttribute("brief", brief);
        session.setAttribute("num", num);
        session.setAttribute("list", listOut);
        request.getRequestDispatcher("blogslist.jsp").forward(request, response);
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
        //title
        String title = request.getParameter("title");

        //category
        String[] category = request.getParameterValues("category");
        List<Integer> categoryId = new ArrayList<>();

        if (category != null) {
            for (String category1 : category) {
                categoryId.add(Integer.parseInt(category1));
            }
        }

        //brief
        String brief = request.getParameter("brief");

        //filter
        PostDAO pdb = new PostDAO();
        List<Post> listFiltered = pdb.filter(title, categoryId, brief, "", 3, "");

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
        
        HttpSession session = request.getSession();
        session.setAttribute("category", categoryId);
        session.setAttribute("title", title);
        session.setAttribute("page", page);
        session.setAttribute("brief", brief);
        session.setAttribute("num", num);
        session.setAttribute("list", list);
        request.getRequestDispatcher("blogslist.jsp").forward(request, response);
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
