<%@page import="kagoyume.UserDataDTO"
        import="kagoyume.KagoyumeHelper"
        import="kagoyume.CartItem"%>
<%
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
    CartItem ci = (CartItem)session.getAttribute("CartItem");
%>
<%-- 
    Document   : myupdateresult
    Created on : 2018/06/24, 16:04:11
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
        <title>情報更新</title>
    </head>
    <body>
        <header class="header">
        <nav class="navbar navbar-default">
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
        <div class="container">
            <h1>ユーザー情報更新完了</h1>
            <table class="table table-bordered">
                <tr><th>ユーザー名：</th><td><%=udd.getName()%></td></tr>
                <tr><th>パスワード：</th><td><%=udd.getPassword()%></td></tr>
                <tr><th>メールアドレス：</th><td><%=udd.getMail()%></td></tr>
                <tr><th>住所：</th><td><%=udd.getAddress()%></td></tr>
            </table>
            <p style="font-size: 200%">以上の内容で更新しました。</p>
        <br>
        <p style="font-size: 150%"><%=KagoyumeHelper.getInstance().home()%></p>
        </div>
    </body>
</html>
