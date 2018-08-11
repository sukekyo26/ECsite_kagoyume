/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumi3
 */
public class MyHistory extends HttpServlet {

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
        ArrayList<Integer> sameDate = new ArrayList<Integer>();
        try {
            BuyDataDTO bdd = new BuyDataDTO();//BuyDataDTOクラスのインスタンスbddを生成
            bdd.setUserID(udd.getUserID());//ユーザーIDをbddに格納
            BuyDataDAO.getInstance().CheckBuyData(bdd);//BuyDataDAOクラスで購入情報をbddに格納
            SearchBuyData sbd = new SearchBuyData();//SearchBuyDataクラスのインスタンスsbdを生成
            sbd.BDD2SBDMapping(bdd);//bddに格納されているデータをsbdに格納
            SearchYahooApi.getInstance().SearchBuyData(sbd);//sbdに格納されている商品コードからYahooAPIで商品コード検索をし、検索したデータをsbdに格納
            int num = 1;
            if(sbd.getItemSize() == 1){
                sameDate.add(num);
                request.setAttribute("itemSameDate", sameDate);
                request.setAttribute("BuyData",sbd);
                request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
                return;
            }
            for(int count = 0; count < sbd.getItemSize() - 1; count++){
                if(sbd.getBuyDate(count).equals(sbd.getBuyDate(count + 1))){//購入日時が同じ物をカウントし、ArrayListに格納
                    num++;
                } else {
                    sameDate.add(num);
                    num = 1; 
                } 
                if(count == sbd.getItemSize() - 2){
                    sameDate.add(num);
                }
            }
            request.setAttribute("itemSameDate", sameDate);
            request.setAttribute("BuyData",sbd);
            request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
        }catch (Exception e){
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
