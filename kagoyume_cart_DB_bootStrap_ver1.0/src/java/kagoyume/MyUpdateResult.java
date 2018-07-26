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
public class MyUpdateResult extends HttpServlet {

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
        UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
        UserData ud = new UserData();
        try {
            request.setCharacterEncoding("UTF-8");
            
            String accesschk = request.getParameter("ac");//不正なアクセスチェック
            if(accesschk == null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            String mail = request.getParameter("mail");
            String adsLv1 = request.getParameter("address-level1");
            String adsLv2 = request.getParameter("address-level2");
            
            
            ud.setName(user); //UserDataクラスに値を格納
            ud.setPassword(password);
            ud.setMail(mail);
            ud.setAddressLevel1(adsLv1);//入力済フォーム用
            ud.setAddressLevel2(adsLv2);//入力済フォーム用
            
            if(!udd.getMail().equals(mail) && UserDataDAO.getInstance().CheckRegisteredUser(ud)){//メールアドレスの重複チェック
                ud.setMailCheck(false);
                request.setAttribute("UserInfo", ud);
                request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
                return;
            }
            //if(adsLv1.endsWith("都") | adsLv1.endsWith("道") || adsLv1.endsWith("府") || adsLv1.endsWith("県")){
            //} else {
              //  ud.setAddressCheck(false);
                //request.setAttribute("UserInfo", ud);
                //request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
                //return;
            //}
            int count = 0;
            if(adsLv2.contains("市")){
                count++;
            }
            if(adsLv2.contains("区")){
                count++;
            }
            if(adsLv2.contains("町")){
                count++;
            }
            if(adsLv2.contains("村")){
                count++;
            }
            if(count < 2){//市区町村の文字が最低でも2つないとおかしいのでその場合は戻る
                ud.setAddressCheck(false);
                request.setAttribute("UserInfo", ud);
                request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
                return;
            }
            UserDataDTO updateudd = new UserDataDTO();//フォームで入力された更新情報を格納する(空欄も)
            updateudd.setAddress(adsLv1 + adsLv2);//住所を結合
            udd.setAddress(adsLv1 + adsLv2);//住所を結合
            
            
            updateudd.setUserId(udd.getUserID());
            updateudd.setName(request.getParameter("user"));

            if(!request.getParameter("user").equals("")){
                udd.setName(request.getParameter("user"));
            }//空欄でない時loginUserDataの値も更新
            updateudd.setPassword(request.getParameter("password"));
            if(!request.getParameter("password").equals("")){
                udd.setPassword(request.getParameter("password"));
            }//空欄でない時loginUserDataの値も更新
            updateudd.setMail(request.getParameter("mail"));
            if(!request.getParameter("mail").equals("")){
                udd.setMail(request.getParameter("mail"));
            }//空欄でない時loginUserDataの値も更新
            
            
            UserDataDAO.getInstance().UserDataUpdate(updateudd);//入力されたユーザー情報を更新
            request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
        } catch(Exception e) {
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
