<%@page import="kagoyume.SearchResult"
        import="kagoyume.UserDataDTO"
        import="kagoyume.KagoyumeHelper"%>
<%
    SearchResult sr = (SearchResult)session.getAttribute("SearchResult");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
%>
<%-- 
    Document   : search
    Created on : 2018/06/28, 14:04:41
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
        <title>検索結果</title>
    </head>
    <body>
        <header class="header">
        <nav class="navbar navbar-default navbar-fixed-top">
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
                    <%if(udd != null){%>
                    <ul class="nav navbar-nav navbar-right">
                        <li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li> 
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i> 買い物かご</a></li>
                        <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                    </ul>
                    <%}else{%>
                    <ul class="nav navbar-nav navbar-right">    
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i> 買い物かご</a></li>
                        <li><a href="Login?previous=search"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                    <%}%>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <br>
        <br>
        <div class="container-fluid">
        <%if(sr.getHitCount() != 0){%>
        <h1 style="font-size: 120%">検索キーワード「<span style="color: red">"<%=sr.getKeyword()%>"</span>」(カテゴリ:<%=sr.getCategoryName()%>/ソート順:<%=sr.getSortName()%><%if(!sr.getHighPrice().equals("")){%>/最高金額設定:<%=sr.getHighPrice()%>円<%}%><%if(!sr.getLowPrice().equals("")){%>/最低金額設定:<%=sr.getLowPrice()%>円<%}%>)の検索結果 <%out.print("--" + sr.getHitCount() + "件表示--");%></h1><br>
        
        <table class="table table-hover">
            <thead>
                <tr><th>サムネイル画像</th><th>商品名</th><th>値段</th></tr>
            </thead>
            <tbody>
            <%for(int number = 0; number < sr.getHitCount(); number++){%>
            <tr><th><img src="<%=sr.getThumnail(number)%>"></th><th><a href="Item?id=<%=number%>"><%=sr.getName(number)%></a></th><th><%=sr.getPrice(number) + "円"%></th><tr>
            <%}%>
            </tbody>
        </table>
        <%} else {%>
        <h1 style="font-size: 120%">検索キーワード「"<%=sr.getKeyword()%>"」に該当する商品はありませんでした(カテゴリ:<%=sr.getCategoryName()%>/ソート順:<%=sr.getSortName()%><%if(!sr.getHighPrice().equals("")){%>/最高金額設定:<%=sr.getHighPrice()%>円<%}%><%if(!sr.getLowPrice().equals("")){%>/最低金額設定:<%=sr.getLowPrice()%>円<%}%>) <%out.print("--" + sr.getHitCount() + "件表示--");%></h1>
        <%}%>
        </div>
        <br>
        <%=kagoyume.KagoyumeHelper.getInstance().home()%>
    </body>
</html>