<%@page import="kagoyume.CartItem"
        import="kagoyume.UserDataDTO"%>
<%
    HttpSession hs = request.getSession();
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
        <title>購入確認</title>
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
            <h1>購入確認</h1>
            <table class="table table-bordered">
                <thead>
                    <tr><th>商品名</th><th>金額</th><th>個数</th></tr>
                </thead>
                <%for(int a = 0; a < ci.getItemSize(); a++){%>
                    <%if(ci.getUserID(a) == udd.getUserID()){%>
                    <tbody>
                    <tr><td><%=ci.getName(a)%></td><td><%=ci.getPrice(a) + "円"%></td><td><%=ci.getItemNumber(a) + "個"%></td></tr>
                    </tbody>
                    <%}%>
                <%}%>
            </table>
                <p style="color: red"><span style="font-size: 150%">合計金額</span><span style="font-size: 300%"><%=ci.getSumPrice(udd)%></span><span style="font-size: 150%">円</span></p>
            <br>
        <form action="BuyComplete" method="post">
            <div class="form-group">
                <p>発送方法</p>
                <label class="radio-inline"><input type="radio" name="type" value="1" checked>ヤマト運輸</label>
                <label class="radio-inline"><input type="radio" name="type" value="2">佐川急便</label>
                <label class="radio-inline"><input type="radio" name="type" value="3">ゆうパック</label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-danger">以上の内容で購入する</button>
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
            </div>
        </form>
            <form action="Cart" method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-default">カートに戻る</button>
            </div>
        </form>
        </div>
    </body>
</html>
