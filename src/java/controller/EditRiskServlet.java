/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.RiskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Risk;
import model.RiskResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "EditRiskServlet", urlPatterns = {"/editR"})
public class EditRiskServlet extends HttpServlet {

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
            out.println("<title>Servlet EditRiskServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRiskServlet at " + request.getContextPath() + "</h1>");
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
            String name = request.getParameter("name");
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            int cateID = Integer.parseInt(category);
            String createdBy = request.getParameter("createdBy");
            String owner = request.getParameter("owner");
            String description = request.getParameter("des");
            String cause= request.getParameter("cause");
            String impact= request.getParameter("impact");
            String poss= request.getParameter("possi");
            String r = request.getParameter("rank");
            int rank = Integer.parseInt(r);
            String sta = request.getParameter("status");
            int status = Integer.parseInt(sta);
            String update = request.getParameter("update");
            String id = request.getParameter("id");
            int ID = Integer.parseInt(id);
            Risk risk = new Risk(ID,name,title,cateID,createdBy,owner,description,cause,poss,impact,rank,status,update);
            RiskDAO dao = new RiskDAO();
            dao.edit(risk);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = dao.getTotalRisk();
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
            List<Risk> list = dao.pagingProduct(index);
            List<Risk> list1 = dao.getAll();
            request.setAttribute("tag", index);
            request.setAttribute("endP", endPage);
            request.setAttribute("listR", list);
            request.setAttribute("listS", list1);
            request.getRequestDispatcher("risk.jsp").forward(request, response);
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
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String riskID = request.getParameter("riskID");
            int id = Integer.parseInt(riskID);
            String action = request.getParameter("action");
            String newP = request.getParameter("newP");
            String newI = request.getParameter("newI");
            String ID = request.getParameter("id");
            int iD = Integer.parseInt(ID);
//            PrintWriter out = response.getWriter();
//            out.print(from+""+to+id+action+" "+newP+" "+newI+" "+iD);
            RiskResponse RS = new RiskResponse(iD,id,action,from,to,newP,newI);
            RiskDAO dao = new RiskDAO();
            dao.editR(RS);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = dao.getTotalRisk();
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
        List<RiskResponse> list = dao.pagingProductR(index);
        List<RiskResponse> list1 = dao.getAllR();
        List<Risk> list2 = dao.getAll();
        request.setAttribute("tag", index);
        request.setAttribute("endP", endPage);
        request.setAttribute("listR", list);
        request.setAttribute("listS", list1);
        request.setAttribute("listID", list2);
        request.getRequestDispatcher("riskR.jsp").forward(request, response);
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
