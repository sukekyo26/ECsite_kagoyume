<%@page import="kagoyume.UserData"
        import="kagoyume.KagoyumeHelper"%>
<%
    UserData ud = (UserData)request.getAttribute("newUser");
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
        <title>ユーザー登録完了</title>
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
                    <a href="Top" class="navbar-brand" style="font-size: 150%; font-family: serif">かごゆめ</a>
                </div>                
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">    
                        <li><a href="Login?previous=top"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <div class="container">
            <div class="panel panel-success">
                <div class="panel-body">
                    <h1 class="text-center">登録完了</h1>
                    <table class="table table-bordered">
                        <tr><th>ユーザー名：</th><td><%=ud.getName()%></td></tr>
                        <tr><th>パスワード：</th><td><%=ud.getPassword()%></td></tr>
                        <tr><th>メールアドレス：</th><td><%=ud.getMail()%></td></tr>
                        <tr><th>住所：</th><td><%=ud.getAddress()%></td></tr>
                    </table>
                    <br>
                    <p>上記の内容で登録しました。</p>    
                </div>
            </div>
        <br>
        <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>
