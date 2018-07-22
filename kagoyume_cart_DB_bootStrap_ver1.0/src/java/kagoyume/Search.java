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
public class Search extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");//検索で入力された日本語パラメータを受け取るために必要
            
            SearchResult sr = new SearchResult();//検索条件,検索結果を保持するクラス
            TopError te = new TopError();//topページのエラー表示処理クラス
            
            
            if(request.getParameter("search").equals("")){
                //名前の入力が無い状態で検索をするとここに来る
                te.setMarkCheck(false);
                request.setAttribute("TopError", te);
                request.getRequestDispatcher("./top.jsp").forward(request, response);
                return;
            }
            
            sr.setKeyword(request.getParameter("search"));//検索キーワードをSerchResultクラスのインスタンスsrにセット
            sr.setCategoryId(request.getParameter("category_id"));
            sr.setSort(request.getParameter("sort"));
            if(request.getParameter("highPrice") != null && !request.getParameter("highPrice").equals("")){
                //最高金額指定の入力があれば中を通るURLに文字列を追加する処理
                String price_to = request.getParameter("highPrice");
                sr.setHighPrice(request.getParameter("highPrice"));
            }
            if(request.getParameter("lowPrice") != null && !request.getParameter("lowPrice").equals("")){
                //最低金額指定の入力があれば中を通るURLに文字列を追加する処理
                String price_from = request.getParameter("lowPrice");
                sr.setLowPrice(request.getParameter("lowPrice"));
            }
            SearchResult newsr = SearchYahooApi.getInstance().SearchByQuery(sr);//SearchYahooApiクラスで商品を検索し、検索結果をSearchResultで返す
            session.setAttribute("ac", (int) (Math.random() * 1000));//不正なアクセス対策
            session.setAttribute("SearchResult", newsr);//新セッション(SearchResult)[検索したデータの上位10件のデータをセットしたSearchResultクラスのインスタンスを格納したセッション]
            request.getRequestDispatcher("./search.jsp").forward(request, response);
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
