/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.User;

import DAO.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ng Quoc Bao
 */
@WebServlet(name = "ForgotPassController", urlPatterns = {"/forgot"})
public class ForgotPassController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("fullname");
        String user = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String mail = request.getParameter("mail");
        String gender = request.getParameter("gender");

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
//        session.setMaxInactiveInterval(10000);

        AccountDAO dao = new AccountDAO();
        boolean check = dao.checkProfile(user, name, phone, address, mail, gender);
        boolean exist = dao.checkAccountExist(user);

        //exist == false, không tồn tại
        if (!exist) {
            request.setAttribute("mess", "Tài khoản không tồn tại!");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        } else {
            //nếu c trả về true thì thông tin trùng khớp
            if (check) {
                request.setAttribute("messSuccess", "Thông tin trùng khớp! Cập nhật mật khẩu mới của bạn!");
                request.getRequestDispatcher("UpdatePassword.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", "Thông tin không trùng khớp!");
                request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
            }
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
        processRequest(request, response);
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
