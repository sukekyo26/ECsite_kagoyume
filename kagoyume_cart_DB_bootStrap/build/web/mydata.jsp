<%@page import="kagoyume.UserDataDTO"
        import="java.text.SimpleDateFormat"
        import="kagoyume.KagoyumeHelper"%>
<%
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
%>
<%-- 
    Document   : mydata
    Created on : 2018/06/24, 16:00:12
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
        <title>ユーザー情報</title>
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
                    <a href="top.jsp" class="navbar-brand" style="font-size: 150%">かごゆめ</a>
                </div>                
                <div class="collapse navbar-collapse" id="navBootstrap">
                    <ul class="nav navbar-nav navbar-right">
                        <li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li> 
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i> 買い物かご</a></li>
                        <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <div class="container">
            <h2><%=udd.getName()%>さんのユーザー情報</h2>
            <table class="table table-bordered">
                <thead></thead>
                <tr><th>ユーザー名</th><td><%=udd.getName()%></td></tr>
                <tr><th>メールアドレス</th><td><%=udd.getMail()%></td></tr>
                <tr><th>住所</th><td><%=udd.getAddress()%></td></tr>            
                <tr><th>登録日時</th><td><%=sdf.format(udd.getNewDate())%></td></tr>
                <tr><th>総購入金額</th><td><%=udd.getTotal()%>円</td></tr>
                <tr><th>購入履歴</th><td><a href="MyHistory">別ページ</a></td></tr>
            </table>
        <br>
        <form action="MyUpdate" method="post">
            <button type="submit" class="btn btn-success">ユーザーデータを更新</button>
        </form>
        <form action="MyDelete" method="post">
            <button type="submit" class="btn btn-danger">ユーザーデータを削除</button>
        </form>
        <br>
        <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>
