<%-- 
    Document   : mydeleteresult
    Created on : 2018/06/24, 16:04:41
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
        <title>削除完了</title>
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
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">    
                        <li><a href="Login?previous=top"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <div class="container">
            <h1 style="font-size: 150%">ユーザーを削除しました。</h1>
            <a href="top.jsp" style="font-size: 100%">トップページへ戻る</a>
        </div>
    </body>
</html>
