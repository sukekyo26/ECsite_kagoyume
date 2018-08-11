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
public class Cart extends HttpServlet {

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
        CartItem ci = (CartItem)session.getAttribute("CartItemGuest");
        CartItem uci = (CartItem)session.getAttribute("CartItem");
        LoginBeans log = (LoginBeans)request.getAttribute("loginInfo");
        UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
        try {
            if(request.getParameter("delete") != null){
                //削除ボタンを押した時
                int number = Integer.parseInt(request.getParameter("delete"));
                CartDataDAO.getInstance().DeleteCartDataByCartID(uci, number);
                uci.removeItem(number);//データベース上から削除してからセッションから削除(セッションにcartIDを格納してるので、セッションから削除すると番号がずれる)
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
            if(udd != null && ci != null){
                //ログイン済でCartItemGuestにアイテムがある場合
                int size = ci.getItemSize();//一つずつセッションからデータベースに追加していき、その都度セッションから消していくのではじめにカート内のアイテムサイズをセットする
                for(int a = 0; a < size; a++){//カート内のアイテムサイズ分だけ繰り返し
                    //カートに入れた商品をデータベース上に登録
                    ci.updateUserID(0, udd.getUserID());
                    CartDataDTO cdd = CartDataDAO.getInstance().SearchCartItem(ci);//ログイン済みのユーザーIDに該当するデータベース上にあるカートの商品の商品コード、個数を取得
                    boolean flag = true;
                    if(cdd.getItemSize() != 0){//データベース上のcart_tが空でない場合
                        for(int count = 0; count < cdd.getItemSize(); count++){
                            if(cdd.getItemCode(count).equals(ci.getItemCode(0))){//一致する商品コードがあった場合
                                int beforeNumber = cdd.getItemNumber(count);
                                CartDataDAO.getInstance().ItemNumberUpdate(ci, beforeNumber);//商品個数のみ更新
                                ci.removeItem(0);
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(flag){//前の処理で一致する商品コードが無かった時にここからcart_tテーブルに格納
                        CartDataDAO.getInstance().CartDataInsert(ci);//セッションCartItemGuest内の値をデータベース上に格納
                        ci.removeItem(0);
                    }
                }
                session.removeAttribute("CartItemGuest");
                CartDataDTO cdd = CartDataDAO.getInstance().CartDataSelect(udd);//ユーザーIDに応じたカートの中身をデータベースから取得
                CartItem nci = SearchYahooApi.getInstance().SearchCartItem(cdd);//YahooAPIから商品コードを使って検索したものをCartItemクラスに格納
                session.setAttribute("CartItem", nci);
                if(log != null && log.getPreviousPage() != null){
                    request.getRequestDispatcher(log.getPreviousPage()).forward(request, response);
                    return;
                }
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
            if(udd != null){//ログイン済みかつCartItemGuestにアイテムが無い
                CartDataDTO cdd = CartDataDAO.getInstance().CartDataSelect(udd);//ユーザーIDに応じたカートの中身をデータベースから取得
                CartItem nci = SearchYahooApi.getInstance().SearchCartItem(cdd);//YahooAPIから商品コードを使って検索したものをCartItemクラスに格納
                session.setAttribute("CartItem", nci);
                if(log != null && log.getPreviousPage() != null){
                    request.getRequestDispatcher(log.getPreviousPage()).forward(request, response);
                    return;
                }
                session.setAttribute("ac", (int) (Math.random() * 1000));//topからいきなり買い物かごを見た時用の不正なアクセス対策
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
            if(request.getParameter("check") != null && udd == null){
                //add.jspから来たかつログインしていない
                LoginBeans lb = new LoginBeans();
                lb.setPreviosPage("Cart");//直接Login.javaに移動する為、parameterが使えないのでCartに手動セット
                lb.setNonLogin(true);
                request.setAttribute("nonLogin", lb);
                request.getRequestDispatcher("Login").forward(request, response);
                return;
            }
            session.setAttribute("CartItem", ci);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
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
