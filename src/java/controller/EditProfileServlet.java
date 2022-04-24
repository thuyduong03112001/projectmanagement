/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.PushImageFromLocal;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;

/**
 *
 * @author DELL
 */
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet EditProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dob");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String bio = request.getParameter("bio");
        Part part = request.getPart("image");
        String image = part.getSubmittedFileName();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        acc.setFullName(fullName);
        acc.setPhoneNumber(phoneNumber);
        acc.setDob(dob);
        acc.setGender(gender);
        acc.setBio(bio);
        String oldImage = acc.getImage();
        acc.setImage(image);
        AccountDAO adb = new AccountDAO();
        boolean ans = adb.updateProfile(acc);
        if (ans) {
            String path = request.getRealPath("/") + "imgs" + File.separator + acc.getImage();
            String pathOldImage = request.getRealPath("/") + "imgs" + File.separator + oldImage;
            if(!oldImage.equals("user.png")){
            PushImageFromLocal.deleteFile(pathOldImage);
            }
            if (PushImageFromLocal.saveFile(part.getInputStream(), path)) {
                      out.print("Update success");
                      String valid = "Update profile success";
                      request.setAttribute("valid", valid);
                } else{
                    String valid = "Something went wrong...";
                    request.setAttribute("valid", valid);
                }          
        } else {
            out.print("Not updated...");
            String valid = "Something went wrong...";
            request.setAttribute("valid", valid);
        }
        response.sendRedirect("userprofile.jsp");
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
