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
public class Add extends HttpServlet {

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
        SelectItem si = (SelectItem)session.getAttribute("SelectItem");
        UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
        CartItem ci = (CartItem)session.getAttribute("CartItemGuest");//セッション("CartItemGuest")の中身がない＝guestが買い物かごに入れた商品が無い
        try {
            //カートに追加ボタンでここに来る。
            
            int itemNum = Integer.parseInt(request.getParameter("number"));
            if(udd != null){//ユーザーログイン済の場合
                CartItem nci = new CartItem(si.getName(), si.getThumnail(), si.getLargeThumnail(), si.getDescription(), si.getPrice(), si.getRate(), itemNum, si.getItemCode());
                //userID,cartIDは初期値0で自動セット
                nci.updateUserID(0, udd.getUserID());//ゲストユーザーのIDは初期値0なのでユーザーのIDに変更
                CartDataDTO cdd = CartDataDAO.getInstance().SearchCartItem(nci);//ユーザーに該当するデータベース上にあるカートの商品の商品コード、個数を取得
                if(cdd.getItemSize() != 0){//空でない場合
                    for(int count = 0; count < cdd.getItemSize(); count++){
                        if(cdd.getItemCode(count).equals(si.getItemCode())){//一致する商品コードがあった場合
                            int beforeNumber = cdd.getItemNumber(count);
                            CartDataDAO.getInstance().ItemNumberUpdate(nci, beforeNumber);//商品個数のみ更新
                            cdd = CartDataDAO.getInstance().CartDataSelect(udd);//ユーザーIDに応じたカートの中身をデータベースから取得
                            nci = SearchYahooApi.getInstance().SearchCartItem(cdd);//YahooAPIから商品コードを使って検索したものをCartItemクラスに格納
                            session.setAttribute("CartItem", nci);
                            request.getRequestDispatcher("/add.jsp").forward(request, response);
                            return;
                        }
                    }
                }
                CartDataDAO.getInstance().CartDataInsert(nci);//新しい商品をcart_tテーブルに登録
                cdd = CartDataDAO.getInstance().CartDataSelect(udd);//ユーザーIDに応じたカートの中身をデータベースから取得
                nci = SearchYahooApi.getInstance().SearchCartItem(cdd);//YahooAPIから商品コードを使って検索したものをCartItemクラスに格納
                session.setAttribute("CartItem", nci);
                request.getRequestDispatcher("/add.jsp").forward(request, response);//ログイン済みの場合はそのままデータベース上に入れるのでsessionには入れない
                return;
            }
            if(ci != null){//ログイン済みでない。セッション("CartItemGuest")の中身がある場合
                if(ci.chkSameItem(si.getName(), itemNum, 0)){
                    //guest(ログインしていない人)が既に同じ名前の商品をカートに入れている場合個数のみを増やし、同じ名前の商品がカートになければ以下の処理を行う
                    ci.allSet(0, si.getName(), si.getThumnail(), si.getLargeThumnail(), si.getDescription(), si.getPrice(), si.getRate(), itemNum, si.getItemCode());
                }
                session.setAttribute("CartItemGuest", ci);
                request.getRequestDispatcher("/add.jsp").forward(request, response);
                return;
            }
            CartItem nci = new CartItem(si.getName(), si.getThumnail(), si.getLargeThumnail(), si.getDescription(), si.getPrice(), si.getRate(), itemNum, si.getItemCode());
            session.setAttribute("CartItemGuest", nci);//新セッション(CartItemGuest)[まだログインしていないのでログイン後にデータベースに追加する為に、仮のカートの中身セッションをセット。］
            request.getRequestDispatcher("/add.jsp").forward(request, response);
            
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
