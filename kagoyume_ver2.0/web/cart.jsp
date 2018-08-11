<%@page import="kagoyume.CartItem"
        import="kagoyume.UserDataDTO"
        import="java.util.ArrayList"
        import="kagoyume.KagoyumeHelper"
        import="kagoyume.SearchError"%>
<%
    SearchError se = (SearchError)request.getAttribute("SearchError");
    CartItem ci = (CartItem)session.getAttribute("CartItem");
    CartItem uci = (CartItem)session.getAttribute("CartItemGuest");
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
        <title>カート</title>
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
                        <%if(udd != null){%><li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li><%}%> 
                        <%if(udd != null){%>
                            <li><a href="Cart"><i class="fas fa-shopping-cart"></i><%if(ci != null && ci.getAllItemCount() > 0){%><span class="label label-warning"><%=ci.getAllItemCount()%></span><%}else if(uci != null && uci.getAllItemCount() > 0){%><span class="label label-warning"><%=uci.getAllItemCount()%></span><%}%> 買い物かご</a></li>
                            <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                        <%}else{%>
                            <li><a href="Cart"><i class="fas fa-shopping-cart"></i><%if(ci != null && ci.getAllItemCount() > 0){%><span class="label label-warning"><%=ci.getAllItemCount()%></span><%}else if(uci != null && uci.getAllItemCount() > 0){%><span class="label label-warning"><%=uci.getAllItemCount()%></span><%}%> 買い物かご</a></li>
                            <li><a href="Login?previous=Cart"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                        <%}%>
                    </ul>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="row">
                <form action="Search" method="GET" class="form-horizontal">
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" style="font-family: sans-serif" for="keywd">キーワード検索：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="keywd" name="search" value="<%if(se != null && se.getKeyword() != null){out.print(se.getKeyword());}%>" placeholder="お探しですか？">
                        </div>
                    </div>
                        <a class="text-right" data-toggle="collapse" href="#collapse" style="font-size: 100%; font-family: sans-serif"><strong>詳細検索を開く</strong></a>
                    <div class="collapse" id="collapse">
                        <div class="form-group container">
                            <label class="col-sm-3 control-label" for="category" style="font-size: 110%; font-family: sans-serif">カテゴリ検索：</label>
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
                            <label class="col-sm-3 control-label" for="sort" style="font-size: 110%; font-family: sans-serif">ソート順：</label>
                            <div class="col-sm-8">
                                <select name="sort" class="form-control" id="sort">
                                    <option value="-score" selected>おすすめ順(デフォルト)</option>
                                    <option value="+price">価格安い順</option>
                                    <option value="-price">価格高い順</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group container">
                            <label class="col-sm-3 control-label" for="highPrice" style="font-size: 110%; font-family: sans-serif">最高金額指定(未記入可)：</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="highPrice" name="highPrice" min="1">
                            </div>
                            <label class="col-sm-1 control-label" style="right: 60px" for="lowPrice">円</label>
                        </div>
                        <div class="form-group container">
                            <label class="col-sm-3 control-label" for="lowPrice" style="font-size: 110%; font-family: sans-serif">最低金額指定(未記入可)：</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="lowPrice" name="lowPrice" min="1">
                            </div>
                            <label class="col-sm-1 control-label" style="right: 60px" for="lowPrice">円</label>
                        </div>
                    </div>
                    <div class="form-group container">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success" style="font-size: 110%; font-family: sans-serif"><i class="fas fa-search"></i>検索</button>
                            <%if(se != null && !se.getMarkCheck()){%><span style="color: red"><%out.print("＊検索キーワードを入力してください＊");%></span><%}%>
                            <input type="hidden" name="pageNum" value="1">
                            <input type="hidden" name="previous" value="cart">
                        </div>
                    </div>
                </form>
            </div>
            <h1><%if(udd != null){out.print(udd.getName());}else{out.print("ゲスト");}%>さんのショッピングカート</h1>
            <br>
            <%if(ci != null && ci.getItemSize() != 0){%>
            <table class="table">
                <thead>
                    <tr><th>サムネイル画像</th><th>商品名</th><th>価格</th><th>数量</th><th></th></tr>
                </thead>
                <%for(int number = 0; number < ci.getItemSize(); number++){%>
                <tbody>
                    <tr>
                        <th><img src="<%=ci.getThumnail(number)%>" alt=""></th>
                        <th>
                            <a href="Item?itemID=<%=number%>&previous=cart">
                                <%=ci.getName(number)%>
                            </a>
                        </th>
                        <th><p style="font-size: 150%"><%=ci.getPrice(number) + "円"%></p></th>
                        <th><p style="font-size: 150%"><%=ci.getItemNumber(number) + "個"%></p></th>
                        <th>
                            <form action="Cart" method="get">
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">削除</button>
                                    <input type="hidden" name="delete" value="<%=number%>">
                                </div>
                            </form>
                        </th>  
                    <tr>
                <tbody>
                <%}%>
            </table>        
            <br>
                <%if(udd != null){%>
                <form action="BuyConfirm" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning btn-lg">レジに進む</button>
                    </div>
                </form>
                <%} else {%>
                <form action="Login" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning btn-lg">レジに進む</button>
                        <input type="hidden" name="previous" value="Cart">
                    </div>
                </form>
                <%}%>
            <%} else {%>
            <p>お客様のショッピングカートに商品はありません。</p>
            <%}%>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>    