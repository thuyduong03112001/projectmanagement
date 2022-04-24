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
@WebServlet(name = "UDPostServlet", urlPatterns = {"/udpost"})
public class UDPostServlet extends HttpServlet {

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
            out.println("<title>Servlet UDPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UDPostServlet at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));
        int statusID = Integer.parseInt(request.getParameter("status"));
        PostDAO pdb = new PostDAO();
        if (pdb.getPostById(id).getHrId() != acc.getId()) {
            response.sendRedirect("postlist");
            return;
        }
        pdb.changeStatus(id, statusID);
        response.sendRedirect("postlist");
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
        if (acc == null || acc.getRoleId() != 5) {
            response.sendRedirect("/workflowbox/");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        PostDAO pdb = new PostDAO();
        if (pdb.getPostById(id).getHrId() != acc.getId()) {
            response.sendRedirect("postlist");
            return;
        }
        String title = request.getParameter("title");
        int cate = Integer.parseInt(request.getParameter("category"));
        String date = request.getParameter("date");
        String content = request.getParameter("content");
        String brief = request.getParameter("brief");
        int statusID = Integer.parseInt(request.getParameter("status"));
        int flag = Integer.parseInt(request.getParameter("featured"));

        Post post = new Post();
        //add pic
        Part part = request.getPart("img");
        post.setThumbnail(pdb.getPostById(id).getThumbnail());
        if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
            ////curent address
            String prefix = getServletContext().getRealPath("/");
            Path realPath = Paths.get(prefix);
            String oldPath = realPath.getParent().getParent() + "\\web\\" + pdb.getPostById(id).getThumbnail();
            PushImageFromLocal.deleteFile(oldPath);
            String path = realPath.getParent().getParent() + "\\web\\imgs\\p" + id + part.getSubmittedFileName();
            PushImageFromLocal.saveFile(part.getInputStream(), path);
            post.setThumbnail("imgs/p" + id + part.getSubmittedFileName());
        }
        //end of pic

        if (pdb.getPostById(id).getHrId() != acc.getId()) {
            response.sendRedirect("postlist");
            return;
        }

        post.setId(id);
        post.setCateId(cate);
        post.setBrief(brief);
        post.setContent(content);
        post.setStatusId(statusID);
        post.setFlag(flag);
        post.setTitle(title);
        post.setDate(date);

        pdb.updatePost(post);
        try {
            Thread.sleep(2000);//time is in ms (1000 ms = 1 second)
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        response.sendRedirect("postdetail?id=" + id);
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
