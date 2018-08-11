<%@page import="kagoyume.UserData"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)session.getAttribute("newUser");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <title>登録確認</title>
    </head>
    <body>
        <header class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">メニュー</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="top.jsp" class="navbar-brand" style="font-size: 150%">かごゆめ</a>
                </div>
            </div>
        </nav>
        </header>
        <div class="container">
            <h1 style="font-size: 200%">登録確認</h1>
            <table class="table table-bordered">
                <tr><th>ユーザー名：</th><td><%=ud.getName()%></td></tr>
                <tr><th>パスワード：</th><td><%=ud.getPassword()%></td></tr>
                <tr><th>メールアドレス：</th><td><%=ud.getMail()%></td></tr>
                <tr><th>住所：</th><td><%=ud.getAddress()%></td></tr>
            </table>
            <br>
            <h2 style="font-size: 150%">上記の内容で登録します。よろしいですか？</h2>
            <form action="RegistrationComplete" method="post">
                <div class="form-group">
                    <button type="submit" style="width:100px" class="btn btn-default">はい
                    <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                    </div>
            </form>
            <br>
            <form action="Registration" method="post">
                <div class="form-group">
                    <button type="submit" style="width:100px" class="btn btn-default">いいえ</button>
                    <input type="hidden" name="previous" value="registrationconfirm">
                </div>
            </form>
        </div>
    </body>
</html>
