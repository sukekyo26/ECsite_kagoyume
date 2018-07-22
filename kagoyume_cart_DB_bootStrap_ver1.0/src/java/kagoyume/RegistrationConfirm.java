/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumi3
 */
public class RegistrationConfirm extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserData ud = new UserData();
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に
            

            String user = request.getParameter("user");
            String password = request.getParameter("password");
            String mail = request.getParameter("mail");
            String adsLv1 = request.getParameter("address-level1");
            String adsLv2 = request.getParameter("address-level2");
            String adsLi1 = request.getParameter("address-line1");
            
            ud.setName(user); //UserDataクラスに値を格納
            ud.setPassword(password);
            ud.setMail(mail);
            ud.setAddressLevel1(adsLv1);//入力済フォーム用
            ud.setAddressLevel2(adsLv2);//入力済フォーム用
            ud.setAddressLine1(adsLi1);//入力済フォーム用
            
            if(UserDataDAO.getInstance().CheckRegisteredUser(ud)){//メールアドレス重複チェック
                ud.setMailCheck(false);
                request.setAttribute("newUser", ud);
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }

            if(adsLv2.endsWith("町") || adsLv2.endsWith("村")){//住所不正値チェック
            } else {
                ud.setAddressCheck(false);
                request.setAttribute("newUser", ud);
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }
            ud.setAddress(adsLv1 + adsLv2 + adsLi1);
            if(user.equals("") || password.equals("") || mail.equals("") || adsLi1.equals("")){//未記入チェック
                ud.setMarkCheck(false);
                request.setAttribute("newUser", ud);
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }    
            ud.setMarkCheck(true);//trueに書き換え
            session.setAttribute("newUser", ud);
            request.getRequestDispatcher("/registrationconfirm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
