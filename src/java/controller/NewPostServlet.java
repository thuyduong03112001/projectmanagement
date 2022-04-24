/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PostDAO;
import dal.PushImageFromLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Post;

/**
 *
 * @author Admin
 */
@MultipartConfig
@WebServlet(name = "NewPostServlet", urlPatterns = {"/newpost"})
public class NewPostServlet extends HttpServlet {

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
            out.println("<title>Servlet NewPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewPostServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("newpost.jsp").forward(request, response);
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
        PostDAO pdb = new PostDAO();
        String title = request.getParameter("title");
        int cate = Integer.parseInt(request.getParameter("category"));
        int hrid = acc.getId(); 
        String date = request.getParameter("date");
        String content = request.getParameter("content");
        String brief = request.getParameter("brief");
        int statusID = Integer.parseInt(request.getParameter("status"));
        int flag = Integer.parseInt(request.getParameter("featured"));
        //add pic
        Part part = request.getPart("img");
            //curent address
        String prefix =  getServletContext().getRealPath("/");
        Path realPath = Paths.get(prefix);
        String path = realPath.getParent().getParent() + "\\web\\imgs\\p" + (pdb.getMaxId() + 1)+ part.getSubmittedFileName();
        PushImageFromLocal.saveFile(part.getInputStream(), path);
        //end of pic
        Post post = new Post();
        post.setStatusId(statusID);
        post.setFlag(flag);
        post.setTitle(title);
        post.setCateId(cate);
        post.setHrId(hrid);
        post.setDate(date);
        post.setThumbnail("imgs\\p" + (pdb.getMaxId() + 1) + part.getSubmittedFileName());
        post.setContent(content);
        post.setBrief(brief);

        pdb.createPost(post);
        response.sendRedirect("postlist");
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
