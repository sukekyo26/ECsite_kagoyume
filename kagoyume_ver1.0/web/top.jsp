<%@page import="kagoyume.TopError" 
        import="kagoyume.UserDataDTO"%>
<%
    TopError te = (TopError)request.getAttribute("TopError");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
%>
<%-- 
    Document   : top
    Created on : 2018/06/24, 15:59:00
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
        <title>かごゆめ</title>
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
                    <%if(udd != null){%>
                    <ul class="nav navbar-nav navbar-right">
                        <li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li> 
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i> 買い物かご</a></li>
                        <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                    </ul>
                    <%}else{%>
                    <ul class="nav navbar-nav navbar-right">    
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i> 買い物かご</a></li>
                        <li><a href="Login?previous=top"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                    <%}%>
                </div>
            </div>
        </nav>
        </header>
        
        <div class="container">
            <div class="row">
            <div class="panel panel-success">
                <div class="panel-body panel-success">
                <h1 class="text-center">商品検索</h1>
                <br>
                <form action="Search" method="GET" class="form-horizontal">
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" for="keywd">キーワード検索(必須)：</label>
                        <div class="col-sm-8">
                        <input type="text" class="form-control" id="keywd" name="search" value="<%if(te != null && te.getKeyword() != null){out.print(te.getKeyword());}%>"><br>
                        </div>
                    </div>
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" for="category">カテゴリ検索：</label>
                        <div class="col-sm-8">
                            <select name="category_id" class="form-control" id="category">
                                <option value="1" selected>すべてのカテゴリ</option>
                                <option value="13457">ファッション</option>
                                <option value="2498">食品</option>
                                <option value="2513">アウトドア、釣り、旅行用品</option>
                                <option value="2500">ダイエット、健康</option>
                                <option value="2501">コスメ、美容、ヘアケア</option>
                                <option value="2502">スマホ、タブレット、パソコン</option>
                                <option value="2504">テレビ、オーディオ、カメラ</option>
                                <option value="2505">家電</option>
                                <option value="2506">家具、インテリア</option>
                                <option value="2507">花、ガーデニング</option>
                                <option value="2508">キッチン、日用品、文具</option>
                                <option value="2503">DIY、工具、ガーデニング</option>
                                <option value="2509">ペット用品、生き物</option>
                                <option value="2510">楽器、手芸、コレクション</option>
                                <option value="2511">ゲーム、おもちゃ</option>
                                <option value="2497">ベビー、キッズ、マタニティ</option>
                                <option value="2512">スポーツ</option>
                                <option value="2514">車、バイク、自転車</option>
                                <option value="2516">CD、音楽ソフト</option>
                                <option value="2517">DVD、映像ソフト</option>
                                <option value="10002">本、雑誌、コミック</option>
                                <option value="47727">レンタル、各種サービス</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" for="sort">ソート順：</label>
                        <div class="col-sm-8">
                            <select name="sort" class="form-control" id="sort">
                                <option value="-score" selected>おすすめ順(デフォルト)</option>
                                <option value="+price">価格安い順</option>
                                <option value="-price">価格高い順</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" for="highPrice">最高金額指定(未記入可)：</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="highPrice" name="highPrice" min="1">
                        </div>
                        <label class="col-sm-1 control-label" style="right: 60px" for="lowPrice">円</label>
                    </div>
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" for="lowPrice">最低金額指定(未記入可)：</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="lowPrice" name="lowPrice" min="1">
                        </div>
                        <label class="col-sm-1 control-label" style="right: 60px" for="lowPrice">円</label>
                    </div>
                    <div class="form-group container">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success">検索</button>
                            <%if(te != null && !te.getMarkCheck()){%><span style="color: red"><%out.print("＊検索キーワードを入力してください＊");%></span><%}%>  
                        </div>
                    </div>
                </form>
                </div>
            </div>
            </div>
        </div>
    </body>
</html>
