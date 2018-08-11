<%@page import="kagoyume.SearchError" 
        import="kagoyume.UserDataDTO"
        import="kagoyume.SearchResult"
        import="kagoyume.CartItem"%>
<%
    SearchError te = (SearchError)request.getAttribute("SearchError");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
    SearchResult sr = (SearchResult)session.getAttribute("PopularItem");
    SearchResult rsr = (SearchResult)session.getAttribute("Recently");
    CartItem uci = (CartItem)session.getAttribute("CartItemGuest");
    CartItem ci = (CartItem)session.getAttribute("CartItem");
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
                    <a href="Top" class="navbar-brand" style="font-size: 150%; font-family: serif">かごゆめ</a>
                </div>                
                <div class="collapse navbar-collapse" id="navBootstrap">
                    <%if(udd != null){%>
                    <ul class="nav navbar-nav navbar-right">
                        <li><p class="navbar-text">ようこそ<a href="MyData"><i class="far fa-address-card"></i><%=udd.getName()%></a>さん!</p></li> 
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i><%if(ci != null && ci.getAllItemCount() > 0){%><span class="label label-warning"><%=ci.getAllItemCount()%></span><%}else if(uci != null && uci.getAllItemCount() > 0){%><span class="label label-warning"><%=uci.getAllItemCount()%></span><%}%> 買い物かご</a></li>
                        <li><a href="Login?set=logout"><strong><i class="fas fa-sign-out-alt"></i> ログアウト</strong></a></li>
                    </ul>
                    <%}else{%>
                    <ul class="nav navbar-nav navbar-right">    
                        <li><a href="Cart"><i class="fas fa-shopping-cart"></i><%if(ci != null && ci.getAllItemCount() > 0){%><span class="label label-warning"><%=ci.getAllItemCount()%></span><%}else if(uci != null && uci.getAllItemCount() > 0){%><span class="label label-warning"><%=uci.getAllItemCount()%></span><%}%> 買い物かご</a></li>
                        <li><a href="Login?previous=Top"><strong><i class="fas fa-sign-in-alt"></i> ログイン</strong></a></li>
                    </ul>
                    <%}%>
                </div>
            </div>
        </nav>
        </header>
        <br>
        <div class="container">
            <div class="row">
                <form action="Search" method="GET" class="form-horizontal">
                    <div class="form-group container">
                        <label class="col-sm-3 control-label" style="font-family: sans-serif" for="keywd">商品検索：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="keywd" name="search" value="<%if(te != null && te.getKeyword() != null){out.print(te.getKeyword());}%>" placeholder="お探しですか？">
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
                            <%if(te != null && !te.getMarkCheck()){%><span style="color: red"><%out.print("＊検索キーワードを入力してください＊");%></span><%}%>
                            <input type="hidden" name="pageNum" value="1">
                            <input type="hidden" name="previous" value="top">
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <div class="aside col-sm-3 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title text-center">カテゴリから探す</h1>
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <tbody>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=13457&sort=-score&pageNum=1">ファッション</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=13457&category_id=2494&sort=-score&pageNum=1">レディースファッション</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=13457&category_id=2495&sort=-score&pageNum=1">メンズファッション</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=13457&category_id=2496&sort=-score&pageNum=1">腕時計、アクセサリー</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2498&sort=-score&pageNum=1">食品</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=2499&sort=-score&pageNum=1">ドリンク、水、お酒</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=4744&sort=-score&pageNum=1">スイーツ、洋菓子</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1167&sort=-score&pageNum=1">和菓子、中華菓子</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=4745&sort=-score&pageNum=1">スナック、お菓子、おつまみ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1234&sort=-score&pageNum=1">米、雑穀、粉類</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=939&sort=-score&pageNum=1">魚介類、海産物</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=998&sort=-score&pageNum=1">肉、ハム、ソーセージ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1034&sort=-score&pageNum=1">フルーツ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1032&sort=-score&pageNum=1">野菜</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1201&sort=-score&pageNum=1">麺類、パスタ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1184&sort=-score&pageNum=1">パン、シリアル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1263&sort=-score&pageNum=1">チーズ、乳製品、卵</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=15015&sort=-score&pageNum=1">惣菜、料理</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=4743&sort=-score&pageNum=1">漬物、佃煮、ふりかけ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=41023&sort=-score&pageNum=1">豆腐、納豆、こんにゃく</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1071&sort=-score&pageNum=1">乾物、乾燥豆類、缶詰</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1273&sort=-score&pageNum=1">調味料、料理の素、油</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1313&sort=-score&pageNum=1">製菓材料、パン材料</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=41952&sort=-score&pageNum=1">非常用食品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2498&category_id=1448&sort=-score&pageNum=1">その他食品</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2513&sort=-score&pageNum=1">アウトドア、釣り、旅行用品</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=2660&sort=-score&pageNum=1">釣り</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=2522&sort=-score&pageNum=1">アウトドア、キャンプ、登山</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=2938&sort=-score&pageNum=1">カヌー、カヤック、ボート</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=3566&sort=-score&pageNum=1">プレジャーボート、ヨット</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=4104&sort=-score&pageNum=1">旅行用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=4105&sort=-score&pageNum=1">海外おみやげ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2513&category_id=2949&sort=-score&pageNum=1">その他アウトドア用品</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2500&sort=-score&pageNum=1">ダイエット、健康</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2022&sort=-score&pageNum=1">コンタクトレンズ、ケア用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=16003&sort=-score&pageNum=1">メガネ、老眼鏡</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=48951&sort=-score&pageNum=1">補聴器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=1948&sort=-score&pageNum=1">ダイエット</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45230&sort=-score&pageNum=1">マッサージ、リラクゼーション</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45229&sort=-score&pageNum=1">アロマグッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45231&sort=-score&pageNum=1">冷え対策、保温グッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45232&sort=-score&pageNum=1">暑さ対策、冷却グッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45489&sort=-score&pageNum=1">花粉症対策グッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45492&sort=-score&pageNum=1">電子タバコ、禁煙グッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45493&sort=-score&pageNum=1">いびき防止グッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=14582&sort=-score&pageNum=1">健康アクセサリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=1874&sort=-score&pageNum=1">健康飲料</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45564&sort=-score&pageNum=1">特定保健用食品(トクホ)</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45224&sort=-score&pageNum=1">バランス栄養、栄養調整食品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=4749&sort=-score&pageNum=1">サプリメント</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2053&sort=-score&pageNum=1">医薬品、医薬部外品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=34752&sort=-score&pageNum=1">鍼、灸、治療器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=34747&sort=-score&pageNum=1">矯正用品、補助ベルト</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45898&sort=-score&pageNum=1">健康管理、計測計</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=45945&sort=-score&pageNum=1">救急用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2004&sort=-score&pageNum=1">衛生日用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2012&sort=-score&pageNum=1">オーラルケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2052&sort=-score&pageNum=1">介護用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2500&category_id=2117&sort=-score&pageNum=1">その他ダイエット、健康</a></li>
                                            </ul>
                                        </div>    
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2501&sort=-score&pageNum=1">コスメ、美容、ヘアケア</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1752&sort=-score&pageNum=1">スキンケア、基礎化粧品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1791&sort=-score&pageNum=1">ベースメイク</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=13709&sort=-score&pageNum=1">コフレ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1772&sort=-score&pageNum=1">メイクアップ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=48912&sort=-score&pageNum=1">まつ毛、つけまつ毛用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46356&sort=-score&pageNum=1">メイク道具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1809&sort=-score&pageNum=1">ネイル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1803&sort=-score&pageNum=1">香水</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46328&sort=-score&pageNum=1">フェイスケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1841&sort=-score&pageNum=1">ボディケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46427&sort=-score&pageNum=1">日焼け止め</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46433&sort=-score&pageNum=1">脱毛、除毛</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46467&sort=-score&pageNum=1">制汗剤、デオドラント</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1831&sort=-score&pageNum=1">レディースヘアケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46714&sort=-score&pageNum=1">メンズヘアケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=46737&sort=-score&pageNum=1">メンズシェービング用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2501&category_id=1849&sort=-score&pageNum=1">メンズスキンケア、メイク</a></li>
                                            </ul>
                                        </div>    
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2502&sort=-score&pageNum=1">スマホ、タブレット、パソコン</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=38338&sort=-score&pageNum=1">スマホ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=21076&sort=-score&pageNum=1">タブレットPC</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=38346&sort=-score&pageNum=1">スマホ、タブレットアクセサリー、周辺機器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=36496&sort=-score&pageNum=1">スマートウォッチ、ウェアラブル端末</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=14242&sort=-score&pageNum=1">ノートパソコン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=14241&sort=-score&pageNum=1">デスクトップパソコン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=49351&sort=-score&pageNum=1">ディスプレイ、モニター</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=39624&sort=-score&pageNum=1">プリンター、複合機</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=2519&sort=-score&pageNum=1">パソコン周辺機器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=88&sort=-score&pageNum=1">PCパーツ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=113&sort=-score&pageNum=1">PCサプライ、アクセサリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=150&sort=-score&pageNum=1">ソフトウェア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=38418&sort=-score&pageNum=1">携帯電話</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2502&category_id=4692&sort=-score&pageNum=1">その他</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2504&sort=-score&pageNum=1">テレビ、オーディオ、カメラ</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2504&category_id=635&sort=-score&pageNum=1">テレビ、映像機器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2504&category_id=660&sort=-score&pageNum=1">オーディオ機器</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2504&category_id=2443&sort=-score&pageNum=1">カメラ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2504&category_id=2260&sort=-score&pageNum=1">望遠鏡、光学機器</a></li>
                                            </ul>
                                        </div>    
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2505&sort=-score&pageNum=1">家電</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=4740&sort=-score&pageNum=1">冷暖房器具、空調家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=5300&sort=-score&pageNum=1">生活家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=587&sort=-score&pageNum=1">キッチン家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=1987&sort=-score&pageNum=1">美容家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=1919&sort=-score&pageNum=1">健康家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=5301&sort=-score&pageNum=1">情報家電</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2505&category_id=577&sort=-score&pageNum=1">電池、充電池</a></li>
                                            </ul>
                                        </div> 
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2506&sort=-score&pageNum=1">家具、インテリア</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3664&sort=-score&pageNum=1">テレビ台、キャビネット</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49282&sort=-score&pageNum=1">ラック、シェルフ、本棚</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49277&sort=-score&pageNum=1">チェスト、衣類収納</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49313&sort=-score&pageNum=1">プラケース、押入れ収納</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49297&sort=-score&pageNum=1">キッチン収納</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49292&sort=-score&pageNum=1">ランドリー、サニタリー収納</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=14709&sort=-score&pageNum=1">ソファ、ソファベッド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3646&sort=-score&pageNum=1">テーブル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3618&sort=-score&pageNum=1">椅子、スツール、座椅子</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3640&sort=-score&pageNum=1">デスク、机</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=27409&sort=-score&pageNum=1">パーテーション、衝立</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=534&sort=-score&pageNum=1">照明、電球</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3653&sort=-score&pageNum=1">ベッド、マットレス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3669&sort=-score&pageNum=1">布団、寝具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49218&sort=-score&pageNum=1">こたつ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3604&sort=-score&pageNum=1">カーテン、ブラインド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3610&sort=-score&pageNum=1">カーペット、ラグ、マット</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=4764&sort=-score&pageNum=1">クッション、座布団</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=48111&sort=-score&pageNum=1">ファブリック、カバー類</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3587&sort=-score&pageNum=1">インテリア時計</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=47938&sort=-score&pageNum=1">ウォールデコレーション</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=3585&sort=-score&pageNum=1">インテリア雑貨</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=28069&sort=-score&pageNum=1">ミラー、ドレッサー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49305&sort=-score&pageNum=1">玄関家具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=49177&sort=-score&pageNum=1">子供部屋家具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2506&category_id=4355&sort=-score&pageNum=1">オフィス家具</a></li>
                                            </ul>
                                        </div>   
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2507&sort=-score&pageNum=1">花、ガーデニング</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=15627&sort=-score&pageNum=1">生花</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4101&sort=-score&pageNum=1">プリザーブドフラワー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4087&sort=-score&pageNum=1">ドライフラワー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=50159&sort=-score&pageNum=1">ハーバリウム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4767&sort=-score&pageNum=1">フラワーアレンジメント</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=15629&sort=-score&pageNum=1">リース</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=5235&sort=-score&pageNum=1">造花、アートフラワー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=50187&sort=-score&pageNum=1">サボテン、多肉植物</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=20258&sort=-score&pageNum=1">観葉植物</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=50184&sort=-score&pageNum=1">人工観葉、フェイクグリーン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=50185&sort=-score&pageNum=1">人工芝</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=15641&sort=-score&pageNum=1">盆栽、苔玉</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4081&sort=-score&pageNum=1">種、種子</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4068&sort=-score&pageNum=1">球根、種芋</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4088&sort=-score&pageNum=1">苗</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=38288&sort=-score&pageNum=1">苗木、植木</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=15632&sort=-score&pageNum=1">水生植物</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=50191&sort=-score&pageNum=1">花瓶、花台</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=15631&sort=-score&pageNum=1">アレンジメント用品、資材</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4036&sort=-score&pageNum=1">園芸用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2507&category_id=4103&sort=-score&pageNum=1">その他花、ガーデニング</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2508&sort=-score&pageNum=1">キッチン、日用品、文具</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4874&sort=-score&pageNum=1">キッチン、台所用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=49617&sort=-score&pageNum=1">スリッパ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=46310&sort=-score&pageNum=1">タオル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=46603&sort=-score&pageNum=1">バス、洗面所用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=46311&sort=-score&pageNum=1">トイレ用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=3955&sort=-score&pageNum=1">洗濯用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=49254&sort=-score&pageNum=1">ゴミ箱、ダストボックス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=3968&sort=-score&pageNum=1">掃除用具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=3689&sort=-score&pageNum=1">芳香剤、消臭剤、除湿剤</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=49596&sort=-score&pageNum=1">蚊取り、防虫、害虫駆除</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=46566&sort=-score&pageNum=1">文具、ステーショナリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4222&sort=-score&pageNum=1">防災、防犯、セーフティ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4180&sort=-score&pageNum=1">冠婚葬祭、宗教用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4150&sort=-score&pageNum=1">カタログギフト</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4151&sort=-score&pageNum=1">ギフト券</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2508&category_id=4249&sort=-score&pageNum=1">その他キッチン、日用品、文具</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2503&sort=-score&pageNum=1">DIY、工具、ガーデニング</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=50266&sort=-score&pageNum=1">庭、ガーデニング</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=4237&sort=-score&pageNum=1">住宅設備</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=3930&sort=-score&pageNum=1">道具、工具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=38095&sort=-score&pageNum=1">材料、部品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=38093&sort=-score&pageNum=1">業務、産業用</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=4442&sort=-score&pageNum=1">オフィス用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2503&category_id=3937&sort=-score&pageNum=1">その他DIY、業務、産業用品</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2509&sort=-score&pageNum=1">ペット用品、生き物</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=4772&sort=-score&pageNum=1">犬用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=4820&sort=-score&pageNum=1">猫用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=4798&sort=-score&pageNum=1">熱帯魚、アクアリウム用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=4816&sort=-score&pageNum=1">鳥用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=32938&sort=-score&pageNum=1">小動物用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=33905&sort=-score&pageNum=1">昆虫用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=33966&sort=-score&pageNum=1">爬虫類、両生類用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=32533&sort=-score&pageNum=1">ペットフード</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=34003&sort=-score&pageNum=1">メモリアル、オーナーズグッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=13588&sort=-score&pageNum=1">生き物、生体</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=49119&sort=-score&pageNum=1">動物用医薬品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2509&category_id=4842&sort=-score&pageNum=1">その他ペット用品、生き物</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2510&sort=-score&pageNum=1">楽器、手芸、コレクション</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2327&sort=-score&pageNum=1">楽器、器材</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2266&sort=-score&pageNum=1">手芸、ハンドクラフト</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2406&sort=-score&pageNum=1">コレクション、趣味</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2191&sort=-score&pageNum=1">画材、アート用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2422&sort=-score&pageNum=1">美術、工芸品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=14828&sort=-score&pageNum=1">コスプレ衣装</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2308&sort=-score&pageNum=1">実験、工作</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=4848&sort=-score&pageNum=1">占い、開運</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2510&category_id=2326&sort=-score&pageNum=1">その他楽器、手芸、コレクション</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2511&sort=-score&pageNum=1">ゲーム、おもちゃ</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2161&sort=-score&pageNum=1">テレビゲーム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2119&sort=-score&pageNum=1">おもちゃ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=15160&sort=-score&pageNum=1">フィギュア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2318&sort=-score&pageNum=1">ラジコン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2156&sort=-score&pageNum=1">ミニカー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=17157&sort=-score&pageNum=1">食玩、プライズ、カプセル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2309&sort=-score&pageNum=1">模型、プラモデル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=38146&sort=-score&pageNum=1">パズル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2169&sort=-score&pageNum=1">ボードゲーム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2420&sort=-score&pageNum=1">トレーディングカード</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2165&sort=-score&pageNum=1">カードゲーム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=4011&sort=-score&pageNum=1">パーティグッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=37865&sort=-score&pageNum=1">乗用玩具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=37853&sort=-score&pageNum=1">季節玩具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2166&sort=-score&pageNum=1">ダーツ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2168&sort=-score&pageNum=1">ビリヤード</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=44818&sort=-score&pageNum=1">囲碁、将棋、麻雀</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2511&category_id=2492&sort=-score&pageNum=1">その他おもちゃ</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2497&sort=-score&pageNum=1">ベビー、キッズ、マタニティ</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=50208&sort=-score&pageNum=1">衛生用品、ヘルスケア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41303&sort=-score&pageNum=1">おむつ、トイレ用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41603&sort=-score&pageNum=1">お風呂、バス用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41607&sort=-score&pageNum=1">授乳、食事用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4251&sort=-score&pageNum=1">ベビー服、シューズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4738&sort=-score&pageNum=1">ベビー用寝具、ベッド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48687&sort=-score&pageNum=1">ベビー用家具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48688&sort=-score&pageNum=1">ベビー用セーフティグッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48678&sort=-score&pageNum=1">抱っこ紐、おんぶ紐</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48679&sort=-score&pageNum=1">スリング</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4263&sort=-score&pageNum=1">ベビーカー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=14937&sort=-score&pageNum=1">ベビーシート、チャイルドシート</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41418&sort=-score&pageNum=1">おもちゃ、教材</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4737&sort=-score&pageNum=1">子ども服、シューズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48689&sort=-score&pageNum=1">子ども用寝具、ベッド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41763&sort=-score&pageNum=1">子ども用家具</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=41190&sort=-score&pageNum=1">バッグ、ランドセル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=43965&sort=-score&pageNum=1">子ども用セーフティグッズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=43995&sort=-score&pageNum=1">子ども用自転車、三輪車</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=25317&sort=-score&pageNum=1">マタニティウエア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=48730&sort=-score&pageNum=1">授乳、産後ウェア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4739&sort=-score&pageNum=1">マタニティ、産後用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=13618&sort=-score&pageNum=1">出産祝い、出産記念品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=14936&sort=-score&pageNum=1">記念、行事用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2497&category_id=4353&sort=-score&pageNum=1">その他ベビー、キッズ、マタニティ用品</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2512&sort=-score&pageNum=1">スポーツ</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3059&sort=-score&pageNum=1">ゴルフ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=2986&sort=-score&pageNum=1">スノーボード</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=2964&sort=-score&pageNum=1">スキー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=2950&sort=-score&pageNum=1">フィットネス、トレーニング</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=14894&sort=-score&pageNum=1">マラソン、ランニング</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3336&sort=-score&pageNum=1">野球</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3113&sort=-score&pageNum=1">サッカー、フットサル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3258&sort=-score&pageNum=1">テニス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3280&sort=-score&pageNum=1">バスケットボール</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3295&sort=-score&pageNum=1">バレーボール</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3227&sort=-score&pageNum=1">ソフトボール</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3287&sort=-score&pageNum=1">バドミントン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3244&sort=-score&pageNum=1">卓球</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3358&sort=-score&pageNum=1">ラグビー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3370&sort=-score&pageNum=1">陸上、トラック、フィールド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=16308&sort=-score&pageNum=1">ヨガ、ピラティス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3001&sort=-score&pageNum=1">武道、格闘技</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=14757&sort=-score&pageNum=1">ダンス、バレエ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3234&sort=-score&pageNum=1">新体操、器械体操</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3212&sort=-score&pageNum=1">ストリート系スポーツ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3305&sort=-score&pageNum=1">マリンスポーツ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=3188&sort=-score&pageNum=1">水泳</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=46557&sort=-score&pageNum=1">コンプレッションウエア</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=46567&sort=-score&pageNum=1">スポーツ用下着（汎用）</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=46625&sort=-score&pageNum=1">スポーツアクセサリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=46578&sort=-score&pageNum=1">スポーツバッグ（汎用）</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=46616&sort=-score&pageNum=1">スポーツケア用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=2952&sort=-score&pageNum=1">体育器具、用品</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2512&category_id=16429&sort=-score&pageNum=1">その他の競技種目</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2514&sort=-score&pageNum=1">車、バイク、自転車</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2514&category_id=41234&sort=-score&pageNum=1">自動車</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2514&category_id=41235&sort=-score&pageNum=1">バイク</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2514&category_id=3174&sort=-score&pageNum=1">自転車</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2516&sort=-score&pageNum=1">CD、音楽ソフト</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=721&sort=-score&pageNum=1">邦楽</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=739&sort=-score&pageNum=1">洋楽</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=15718&sort=-score&pageNum=1">KーPOP</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=761&sort=-score&pageNum=1">ワールドミュージック</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=5401&sort=-score&pageNum=1">アニメ、ゲーム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=772&sort=-score&pageNum=1">キッズ、ファミリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=726&sort=-score&pageNum=1">インディーズ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=748&sort=-score&pageNum=1">クラシック</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=744&sort=-score&pageNum=1">ジャズ、フュージョン</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=5388&sort=-score&pageNum=1">ヒーリング、ニューエイジ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=5394&sort=-score&pageNum=1">サウンドトラック</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=5403&sort=-score&pageNum=1">インストゥルメンタル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=846&sort=-score&pageNum=1">実用</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=851&sort=-score&pageNum=1">その他</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2516&category_id=16509&sort=-score&pageNum=1">チケット</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=2517&sort=-score&pageNum=1">DVD、映像ソフト</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=868&sort=-score&pageNum=1">邦画</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=853&sort=-score&pageNum=1">洋画</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=881&sort=-score&pageNum=1">アニメーション</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=901&sort=-score&pageNum=1">ミュージック</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=884&sort=-score&pageNum=1">テレビドラマ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=5608&sort=-score&pageNum=1">お笑い、バラエティ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=900&sort=-score&pageNum=1">アイドル、イメージ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=908&sort=-score&pageNum=1">スポーツ、フィットネス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=2469&sort=-score&pageNum=1">キッズ、ファミリー</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=5615&sort=-score&pageNum=1">演劇、ミュージカル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=920&sort=-score&pageNum=1">趣味、実用、教養</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=2517&category_id=937&sort=-score&pageNum=1">その他</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=10002&sort=-score&pageNum=1">本、雑誌、コミック</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10251&sort=-score&pageNum=1">コミック、アニメ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12492&sort=-score&pageNum=1">文芸</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10003&sort=-score&pageNum=1">雑誌</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10141&sort=-score&pageNum=1">アイドル写真集</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10725&sort=-score&pageNum=1">ビジネス、経済</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10604&sort=-score&pageNum=1">コンピュータ</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10149&sort=-score&pageNum=1">エンターテインメント</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11820&sort=-score&pageNum=1">子ども</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12243&sort=-score&pageNum=1">生活</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11998&sort=-score&pageNum=1">趣味</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11505&sort=-score&pageNum=1">芸術</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=13190&sort=-score&pageNum=1">歴史、心理、教育</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11270&sort=-score&pageNum=1">学習参考書</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11676&sort=-score&pageNum=1">語学、辞書</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10953&sort=-score&pageNum=1">医学、薬学、看護</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=10209&sort=-score&pageNum=1">ゲーム攻略本</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12425&sort=-score&pageNum=1">地図、ガイド</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12204&sort=-score&pageNum=1">就職、資格</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12851&sort=-score&pageNum=1">法律、社会</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=12996&sort=-score&pageNum=1">理学、工学</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11430&sort=-score&pageNum=1">楽譜、音楽書</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=15713&sort=-score&pageNum=1">電子書籍</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=10002&category_id=11493&sort=-score&pageNum=1">関連グッズ</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                    <tr><td>
                                        <div class="dropdown">
                                            <a href="Search?category_id=47727&sort=-score&pageNum=1">レンタル、各種サービス</a>
                                            <a data-toggle="dropdown"><i class="fas fa-angle-down"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47824&sort=-score&pageNum=1">お掃除、訪問サービス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47776&sort=-score&pageNum=1">レンタル</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47889&sort=-score&pageNum=1">リフォーム</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47811&sort=-score&pageNum=1">衣料品お直し</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47817&sort=-score&pageNum=1">クリーニング</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47898&sort=-score&pageNum=1">ペット関連サービス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47909&sort=-score&pageNum=1">サービスクーポン、引換券</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=48831&sort=-score&pageNum=1">車関連サービス</a></li>
                                                <li role="presentation"><a href="Search?b_cate_id=47727&category_id=47905&sort=-score&pageNum=1">その他サービス</a></li>
                                            </ul>
                                        </div>
                                    </td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>    
                </div>
                <div class="contents col-sm-9 col-xs-12">
                <%if(rsr != null){%>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 class="panel-title" style="font-size: 150%">最近チェックした商品</h1>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <%for(int number = rsr.getItemSize() - 1; number >= 0; number--){%>
                            <!--itemTypeにセッション名をクエリストリングに追加-->
                            <li class="list-group-item col-lg-3 col-md-3 col-sm-3 col-xs-4 text-center"><a href="Item?id=<%=number%>&previous=top&itemType=Recently"><img src="<%=rsr.getThumnail(number)%>" class="img-thumbnail" data-toggle="tooltip" data-placement="bottom" title="<%=rsr.getName(number)%>"></a></li>
                            <%}%>
                        </ul>
                    </div>
                </div>
                <%}%>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 class="panel-title" style="font-size: 150%">今売れている商品</h1>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <%for(int number = 0; number < 12; number++){%>
                            <li class="list-group-item col-lg-3 col-md-3 col-sm-3 col-xs-4 text-center"><a href="Item?id=<%=number%>&previous=top&itemType=PopularItem"><img src="<%=sr.getThumnail(number)%>" class="img-thumbnail" data-toggle="tooltip" data-placement="bottom" title="<%=sr.getName(number)%>"></a></li>
                            <%}%>
                        </ul>
                    </div>
                </div>
                </div>
            </div>
        </div>           
    </body>
</html>
