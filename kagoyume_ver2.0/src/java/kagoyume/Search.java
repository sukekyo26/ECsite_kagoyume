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
            //session.removeAttribute("SearchResult");
            SearchResult sr = new SearchResult();//検索条件,検索結果を保持するクラス
            SearchError se = new SearchError();//検索関連のエラー表示処理クラス
            
            if(request.getParameter("sortChange") != null && request.getParameter("sortChange").equals("true")){
                //search.jspでソートを変えた場合のみここを通る
                SearchResult upsr = (SearchResult)session.getAttribute("SearchResult");
                sr.setSort(request.getParameter("sort"));
                if(!upsr.getKeyword().equals("")){sr.setKeyword(upsr.getKeyword());}
                if(!upsr.getB_cateId().equals("")){sr.setB_cateId(upsr.getB_cateId());}
                if(!upsr.getCategoryId().equals("")){sr.setCategoryId(upsr.getCategoryId());}
                if(!upsr.getHighPrice().equals("")){sr.setHighPrice(upsr.getHighPrice());}
                if(!upsr.getLowPrice().equals("")){sr.setLowPrice(upsr.getLowPrice());}
                SearchResult newsr = SearchYahooApi.getInstance().SearchItem(sr);//SearchYahooApiクラスで商品を検索し、検索結果をSearchResultで返す
                session.setAttribute("SearchResult", newsr);
                request.getRequestDispatcher("./search.jsp").forward(request, response);
                return;
            }
            if(request.getParameter("category_id").equals("1") && request.getParameter("search").equals("")){
                //検索ワードの入力が無いかつカテゴリ検索ですべてのカテゴリを選択している状態で検索をするとここに来る
                se.setMarkCheck(false);
                request.setAttribute("SearchError", se);
                String previous = request.getParameter("previous");
                if(!Character.isUpperCase(previous.charAt(0))){//パラメータで送られてきたpreviousの頭文字が大文字の場合サーブレットへ、小文字の場合jspへ
                    previous = "/" + previous + ".jsp";//jspページのリンクをセット
                }
                request.getRequestDispatcher(previous).forward(request, response);
                return;
            }
            if(request.getParameter("search") != null && !request.getParameter("search").equals("")){
                sr.setKeyword(request.getParameter("search"));
            }
            if(request.getParameter("b_cate_id") != null && !request.getParameter("b_cate_id").equals("")){
                sr.setB_cateId(request.getParameter("b_cate_id"));
            } 
            if(request.getParameter("category_id") != null && !request.getParameter("category_id").equals("")){
                sr.setCategoryId(request.getParameter("category_id"));
            }
            if(request.getParameter("sort") != null && !request.getParameter("sort").equals("")){
                sr.setSort(request.getParameter("sort"));
            }
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
            SearchResult newsr = SearchYahooApi.getInstance().SearchItem(sr);//SearchYahooApiクラスで商品を検索し、検索結果をSearchResultで返す
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
