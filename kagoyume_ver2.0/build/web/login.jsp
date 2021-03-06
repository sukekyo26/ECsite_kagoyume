<%@page import="kagoyume.LoginBeans"
        import="kagoyume.KagoyumeHelper"%>
<%
    LoginBeans lb = (LoginBeans)request.getAttribute("loginInfo");
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
        <title>ログイン</title>
    </head>
    <body>
        <header class="header">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="Top" class="navbar-brand" style="font-size: 150%; font-family: serif">かごゆめ</a>
                </div>                
            </div>
        </nav>
        </header>
        <div class="container">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h1 class="text-center">ログイン</h1>
                    <form action="Login" method="post">
                        <div class="form-group">
                            <label for="mail">メールアドレス：</label>
                            <input type="text" name="mail" class="form-control" id="mail">
                        </div>
                        <div class="form-group">
                            <label for="password">パスワード：</label>
                            <input type="password" name="password" class="form-control" id="password">
                        </div>
                        <br><br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">ログイン</button>
                            <input type="hidden" name="previousPage" value="<%=lb.getPreviousPage()%>">
                            <input type="hidden" name="set" value="login">
                        </div>
                    </form>
                    <%if(lb.getLoginFail()){%>
                    <p style="color: red">＊ログインに失敗しました＊</p>
                    <%}%>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <a href="Registration">新規会員登録</a><br>
                </div>
            </div>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>
