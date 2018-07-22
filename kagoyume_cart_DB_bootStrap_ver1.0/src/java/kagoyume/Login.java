/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumi3
 */
public class Login extends HttpServlet {

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
        LoginBeans log = (LoginBeans)request.getAttribute("nonLogin");
        CartItem ci = (CartItem)session.getAttribute("CartItem");
        try {
            request.setCharacterEncoding("UTF-8");
            
            LoginBeans lb = new LoginBeans();
            //ログインで記入したデータとSQLのデータを参照し一致していれば、元いたページに戻る。また、一致していない場合はlogin.jspに行き、IDパスワードが違いますと表示
            if(log != null && log.getNonLogin()){//add.jsp→cart.java→Login.javaの順で来た場合
                lb.setPreviosPage(log.getPreviousPage());
                request.setAttribute("loginInfo", lb);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            if(request.getParameter("set") != null && request.getParameter("set").equals("login")){
                //ログイン画面でログインボタンを押した場合の処理
                UserDataDTO udd = new UserDataDTO();
                udd.setMail(request.getParameter("mail"));
                udd.setPassword(request.getParameter("password"));
                if(UserDataDAO.getInstance().CheckUserData(udd)){
                    //ログイン画面で入力したユーザー名パスワードがデータベース上存在する場合
                    UserDataDTO loginUserData = UserDataDAO.getInstance().LoginUserData(udd);
                    session.removeAttribute("CartItem");//ゲスト状態でカートを見た場合、既にsession("CartItem")に情報が格納されているのでremove
                    session.setAttribute("loginUserData", loginUserData);//ログインに成功したユーザー情報を格納
                    request.getRequestDispatcher(request.getParameter("previousPage")).forward(request, response);//このパラメータ値はlogin.jspから
                    return;
                } else {
                    //ログイン画面で入力したユーザー名パスワードがデータベース上に存在しない場合
                    lb.setPreviosPage(request.getParameter("previousPage"));
                    lb.setLoginFail(true);
                    request.setAttribute("loginInfo", lb);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
            }
            
            if(request.getParameter("set") != null && request.getParameter("set").equals("logout")){
                //ログアウトした時の処理
                session.removeAttribute("loginUserData");
                session.removeAttribute("CartItem");
                request.getRequestDispatcher("/top.jsp").forward(request, response);
                return;
            }
            //ログインしていない場合(はじめはここからスタート)
            String prest = request.getParameter("previous");
            if(Character.isUpperCase(prest.charAt(0))){//パラメータで送られてきたpreviousの頭文字が大文字の場合サーブレットへ、小文字の場合jspへ
                lb.setPreviosPage(prest);//前にいたページのリンクをセット
            } else {
                lb.setPreviosPage("/" + prest + ".jsp");//前にいたページのリンクをセット
            }
            request.setAttribute("loginInfo", lb);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }catch(Exception e){
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
