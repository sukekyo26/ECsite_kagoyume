
<%@page import="kagoyume.SelectItem"
        import="kagoyume.UserDataDTO"
        import="kagoyume.KagoyumeHelper"%>
<%
    SelectItem si = (SelectItem)session.getAttribute("SelectItem");
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
        <title>商品詳細</title>
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
                        <li><a href="Login?previous=item"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                    <%}%>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h1 class="text-center">詳細情報</h1>
                </div>
                <div class="panel-body">
                    <p><img src="<%=si.getLargeThumnail()%>" class="img-thumbnail"></p>
                    <h2>商品名</h2>
                    <p><%=si.getName()%></p>
                    <h2>商品説明</h2>
                    <p><%=si.getDescription()%></p>
                    <h3>評価点</h3>
                    <p><%=si.getRate()%>点</p>
                    <h3><font color="red">価格 <font size="6"><%=si.getPrice()%></font>円　(税込)</font></h3>
                    <form action="Add" method="GET" class="form-inline">
                        <div class="form-group">
                            <label class="sr-only" for="num">数量：</label>
                            <select name="number" class="form-control" id="num">
                                <%for(int num =1; num < 11; num++){%>
                                <option value="<%=num%>"><%=num%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">カートに追加</button>
                        </div>
                    </form>
                </div>
            </div> 
            <br>
            <%=KagoyumeHelper.getInstance().home()%>      
        </div>
    </body>
</html>
