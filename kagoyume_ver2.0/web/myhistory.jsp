<%@page import="java.util.ArrayList"
        import="kagoyume.UserDataDTO"
        import="kagoyume.SearchBuyData"
        import="kagoyume.KagoyumeHelper"
        import="kagoyume.CartItem"%>
<%
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
    SearchBuyData sbd = (SearchBuyData)request.getAttribute("BuyData");
    ArrayList<Integer> sameDate = (ArrayList)request.getAttribute("itemSameDate");
    CartItem ci = (CartItem)session.getAttribute("CartItem");
%>
<%-- 
    Document   : myhistory
    Created on : 2018/06/24, 16:03:42
    Author     : sumi3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>購入履歴</title>
    </head>
    <body>
        <header class="header">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navBootstrap">
                        <span class="sr-only">メニュー</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="Top" class="navbar-brand" style="font-size: 150%; font-family: serif">かごゆめ</a>
                </div>                
                <div class="collapse navbar-collapse" id="navBootstrap">
                    <ul class="nav navbar-nav navbar-right">
                        <li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li> 
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i><%if(ci != null && ci.getAllItemCount() > 0){%><span class="label label-warning"><%=ci.getAllItemCount()%></span><%}%> 買い物かご</a></li>
                        <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <br>
        <br>
        <div class="container">
            <h1 style="font-size: 150%"><%=udd.getName()%>さんの購入履歴</h1>
            <%if(sbd.getItemSize() != 0){%>
                <%
                    int itemNum = 0;
                    int buyDateNum = 0;
                    int price = 0;
                    int priceNum = 0;
                %>
                <%for(int count = 0; count < sameDate.size(); count++){%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            購入日時：<%=sbd.getBuyDate(buyDateNum)%>
                            <%
                                for(int pnum = 0; pnum < sameDate.get(count); pnum++){
                                    price += sbd.getItemPrice(priceNum) * sbd.getItemNumber(priceNum);
                                    priceNum++;
                                }
                                out.print("合計金額：" + price + "円");
                                price = 0;
                            %>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">    
                            <%for(int num = 0; num < sameDate.get(count); num++){%>
                                <tbody>
                                    <tr>
                                        <td>
                                            <img src="<%=sbd.getThumnail(itemNum)%>">
                                        </td>
                                        <td>
                                            <%=sbd.getItemName(itemNum)%>
                                        </td>
                                        <td>
                                            <%=sbd.getItemPrice(itemNum)%>円
                                        </td>
                                        <td>
                                            <%=sbd.getItemNumber(itemNum)%>
                                        </td>
                                        <td>
                                            <%if(sbd.getType(itemNum) == 1){out.print("ヤマト運輸");} else if(sbd.getType(itemNum) == 2){out.print("佐川急便");} else {out.print("ゆうパック");}%>
                                        </td>
                                    </tr>
                                </tbody>
                                <%itemNum++;%>              
                            <%}%>
                            </table>
                        <%buyDateNum += sameDate.get(count);%>  
                        </div>
                    </div>
                <%}%>
            <%}else{%>
            <h2 style="font-size: 100%">お客様の購入履歴はありません</h2>
            <%}%>
            <br>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>