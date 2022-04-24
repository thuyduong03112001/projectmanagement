/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PostDAO;
import dal.PushImageFromLocal;
import dal.SliderDAO;
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
import model.Slider;

/**
 *
 * @author Admin
 */
@MultipartConfig
@WebServlet(name = "UDSliderServlet", urlPatterns = {"/udslider"})
public class UDSliderServlet extends HttpServlet {

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
            out.println("<title>Servlet UDSliderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UDSliderServlet at " + request.getContextPath() + "</h1>");
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
        SliderDAO pdb = new SliderDAO();
        pdb.changeStatus(id, statusID);
        response.sendRedirect("sliderlist");
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
        String title = request.getParameter("title");
        int statusID = Integer.parseInt(request.getParameter("status"));
        int id = Integer.parseInt(request.getParameter("id"));
        String note = request.getParameter("note");
        SliderDAO pdb = new SliderDAO();
        Slider post = new Slider();
        //add pic
        Part part = request.getPart("img");
        post.setImage(pdb.getSliderById(id).getImage());
        if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
            ////curent address
            String prefix = getServletContext().getRealPath("/");
            Path realPath = Paths.get(prefix);
            String oldPath = realPath.getParent().getParent() + "\\web\\" + pdb.getSliderById(id).getImage();
            PushImageFromLocal.deleteFile(oldPath);
            String path = realPath.getParent().getParent() + "\\web\\imgs\\sl" + id + part.getSubmittedFileName();
            PushImageFromLocal.saveFile(part.getInputStream(), path);
            post.setImage("imgs/sl" + id + part.getSubmittedFileName());
        }
        //end of pic

        post.setId(id);
        post.setStatusId(statusID);
        post.setTitle(title);
        post.setNote(note);
        
        pdb.updatePost(post);
        response.sendRedirect("sliderdetail?id=" + id);
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
