<%@page import="kagoyume.CartItem"
        import="kagoyume.UserDataDTO"
        import="java.util.ArrayList"
        import="kagoyume.KagoyumeHelper"%>
<%
    CartItem ci = (CartItem)session.getAttribute("CartItem");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
%>

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
        <title>カート</title>
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
                    <a href="top.jsp" class="navbar-brand" style="font-size: 150%">かごゆめ</a>
                </div>                
                <div class="collapse navbar-collapse" id="navBootstrap">
                    <ul class="nav navbar-nav navbar-right">
                        <%if(udd != null){%><li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li><%}%> 
                        <%if(udd != null){%>
                            <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                        <%}else{%>
                            <li><a href="Login?previous=Cart"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                        <%}%>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <br>
        <br>
        <div class="container">
            <h1><%if(udd != null){out.print(udd.getName());}else{out.print("ゲスト");}%>さんのショッピングカート</h1>
            <br>
            <%if(ci != null && ci.getItemSize() != 0){%>
            <table class="table">
                <thead>
                    <tr><th>サムネイル画像</th><th>商品名</th><th>価格</th><th>数量</th><th></th></tr>
                </thead>
                <%for(int number = 0; number < ci.getItemSize(); number++){%>
                <tbody>
                    <tr>
                        <th><img src="<%=ci.getThumnail(number)%>" alt=""></th>
                        <th>
                            <a href="Item?itemID=<%=number%>&previous=cart">
                                <%=ci.getName(number)%>
                            </a>
                        </th>
                        <th><p style="font-size: 150%"><%=ci.getPrice(number) + "円"%></p></th>
                        <th><p style="font-size: 150%"><%=ci.getItemNumber(number) + "個"%></p></th>
                        <th>
                            <form action="Cart" method="get">
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">削除</button>
                                    <input type="hidden" name="delete" value="<%=number%>">
                                </div>
                            </form>
                        </th>  
                    <tr>
                <tbody>
                <%}%>
            </table>        
            <br>
                <%if(udd != null){%>
                <form action="BuyConfirm" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning btn-lg">レジに進む</button>
                    </div>
                </form>
                <%} else {%>
                <form action="Login" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning btn-lg">レジに進む</button>
                        <input type="hidden" name="previous" value="Cart">
                    </div>
                </form>
                <%}%>
            <%} else {%>
            <p>お客様のショッピングカートに商品はありません。</p>
            <%}%>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>    