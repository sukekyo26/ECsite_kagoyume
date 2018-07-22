<%@page import="kagoyume.UserData"
        import="kagoyume.KagoyumeHelper"
        import="kagoyume.UserDataDTO"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)request.getAttribute("UserInfo");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");
%>
<%-- 
    Document   : myupdate
    Created on : 2018/06/24, 16:03:56
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
        <title>ユーザーデータ更新</title>
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
            <div class="panel panel-success">
                <div class="panel-body">
                    <h1 style="font-size: 200%">更新したいデータを記入してください(住所以外の未記入の欄は更新されません)</h1>
                    <form action="MyUpdateResult" method="post">
                        <div class="form-group">
                            <label for="user">ユーザー名：</label>
                            <input type="text" name="user" class="form-control" id="user" value="<%if(ud != null){out.print(ud.getName());}%>">
                        </div>
                        <div class="form-group">
                            <label for="pass">パスワード：</label>
                            <input type="text" name="password" class="form-control" id="pass" value="<%if(ud != null){out.print(ud.getPassword());}%>">
                        </div>
                        <div class="form-group">
                            <label for="mail">メールアドレス：</label>
                            <input type="email" name="mail" class="form-control" id="mail" value="<%if(ud != null){out.print(ud.getMail());}%>">
                        </div>
                        <div class="form-group">
                            <label for="address">住所(必須)：</label>
                            <select class="form-control" name="address-level1" id="address">
                                <option value="北海道" <%if(ud.getAddressLevel1().equals("北海道")){out.print("selected");}%>>北海道</option>
                                <option value="青森県" <%if(ud.getAddressLevel1().equals("青森県")){out.print("selected");}%>>青森県</option>
                                <option value="岩手県" <%if(ud.getAddressLevel1().equals("岩手県")){out.print("selected");}%>>岩手県</option>
                                <option value="宮城県" <%if(ud.getAddressLevel1().equals("宮城県")){out.print("selected");}%>>宮城県</option>
                                <option value="秋田県" <%if(ud.getAddressLevel1().equals("秋田県")){out.print("selected");}%>>秋田県</option>
                                <option value="山形県" <%if(ud.getAddressLevel1().equals("山形県")){out.print("selected");}%>>山形県</option>
                                <option value="福島県" <%if(ud.getAddressLevel1().equals("福島県")){out.print("selected");}%>>福島県</option>
                                <option value="茨城県" <%if(ud.getAddressLevel1().equals("茨城県")){out.print("selected");}%>>茨城県</option>
                                <option value="栃木県" <%if(ud.getAddressLevel1().equals("栃木県")){out.print("selected");}%>>栃木県</option>
                                <option value="群馬県" <%if(ud.getAddressLevel1().equals("群馬県")){out.print("selected");}%>>群馬県</option>
                                <option value="埼玉県" <%if(ud.getAddressLevel1().equals("埼玉県")){out.print("selected");}%>>埼玉県</option>
                                <option value="千葉県" <%if(ud.getAddressLevel1().equals("千葉県")){out.print("selected");}%>>千葉県</option>
                                <option value="東京都" <%if(ud.getAddressLevel1().equals("東京都")){out.print("selected");}%>>東京都</option>
                                <option value="神奈川県" <%if(ud.getAddressLevel1().equals("神奈川県")){out.print("selected");}%>>神奈川県</option>
                                <option value="新潟県" <%if(ud.getAddressLevel1().equals("新潟県")){out.print("selected");}%>>新潟県</option>
                                <option value="富山県" <%if(ud.getAddressLevel1().equals("富山県")){out.print("selected");}%>>富山県</option>
                                <option value="石川県" <%if(ud.getAddressLevel1().equals("石川県")){out.print("selected");}%>>石川県</option>
                                <option value="福井県" <%if(ud.getAddressLevel1().equals("福井県")){out.print("selected");}%>>福井県</option>
                                <option value="山梨県" <%if(ud.getAddressLevel1().equals("山梨県")){out.print("selected");}%>>山梨県</option>
                                <option value="長野県" <%if(ud.getAddressLevel1().equals("長野県")){out.print("selected");}%>>長野県</option>
                                <option value="岐阜県" <%if(ud.getAddressLevel1().equals("岐阜県")){out.print("selected");}%>>岐阜県</option>
                                <option value="静岡県" <%if(ud.getAddressLevel1().equals("静岡県")){out.print("selected");}%>>静岡県</option>
                                <option value="愛知県" <%if(ud.getAddressLevel1().equals("愛知県")){out.print("selected");}%>>愛知県</option>
                                <option value="三重県" <%if(ud.getAddressLevel1().equals("三重県")){out.print("selected");}%>>三重県</option>
                                <option value="滋賀県" <%if(ud.getAddressLevel1().equals("滋賀県")){out.print("selected");}%>>滋賀県</option>
                                <option value="京都府" <%if(ud.getAddressLevel1().equals("京都府")){out.print("selected");}%>>京都府</option>
                                <option value="大阪府" <%if(ud.getAddressLevel1().equals("大阪府")){out.print("selected");}%>>大阪府</option>
                                <option value="兵庫県" <%if(ud.getAddressLevel1().equals("兵庫県")){out.print("selected");}%>>兵庫県</option>
                                <option value="奈良県" <%if(ud.getAddressLevel1().equals("奈良県")){out.print("selected");}%>>奈良県</option>
                                <option value="和歌山県" <%if(ud.getAddressLevel1().equals("和歌山県")){out.print("selected");}%>>和歌山県</option>
                                <option value="鳥取県" <%if(ud.getAddressLevel1().equals("鳥取県")){out.print("selected");}%>>鳥取県</option>
                                <option value="島根県" <%if(ud.getAddressLevel1().equals("島根県")){out.print("selected");}%>>島根県</option>
                                <option value="岡山県" <%if(ud.getAddressLevel1().equals("岡山県")){out.print("selected");}%>>岡山県</option>
                                <option value="広島県" <%if(ud.getAddressLevel1().equals("広島県")){out.print("selected");}%>>広島県</option>
                                <option value="山口県" <%if(ud.getAddressLevel1().equals("山口県")){out.print("selected");}%>>山口県</option>
                                <option value="徳島県" <%if(ud.getAddressLevel1().equals("徳島県")){out.print("selected");}%>>徳島県</option>
                                <option value="香川県" <%if(ud.getAddressLevel1().equals("香川県")){out.print("selected");}%>>香川県</option>
                                <option value="愛媛県" <%if(ud.getAddressLevel1().equals("愛媛県")){out.print("selected");}%>>愛媛県</option>
                                <option value="高知県" <%if(ud.getAddressLevel1().equals("高知県")){out.print("selected");}%>>高知県</option>
                                <option value="福岡県" <%if(ud.getAddressLevel1().equals("福岡県")){out.print("selected");}%>>福岡県</option>
                                <option value="佐賀県" <%if(ud.getAddressLevel1().equals("佐賀県")){out.print("selected");}%>>佐賀県</option>
                                <option value="長崎県" <%if(ud.getAddressLevel1().equals("長崎県")){out.print("selected");}%>>長崎県</option>
                                <option value="熊本県" <%if(ud.getAddressLevel1().equals("熊本県")){out.print("selected");}%>>熊本県</option>
                                <option value="大分県" <%if(ud.getAddressLevel1().equals("大分県")){out.print("selected");}%>>大分県</option>
                                <option value="宮崎県" <%if(ud.getAddressLevel1().equals("宮崎県")){out.print("selected");}%>>宮崎県</option>
                                <option value="鹿児島県" <%if(ud.getAddressLevel1().equals("鹿児島県")){out.print("selected");}%>>鹿児島県</option>
                                <option value="沖縄県" <%if(ud.getAddressLevel1().equals("沖縄県")){out.print("selected");}%>>沖縄県</option>
                            </select>
                            <input type="text" name="address-level2" placeholder="市区以降" value="<%if(ud != null){out.print(ud.getAddressLevel2());}%>" class="form-control" id="address">
                        </div>
                        <div>
                        <%if(ud != null && !ud.getAddressCheck()){%><p style="color: red"><%out.print("＊住所の値が不正です＊");%></p><%}%>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">更新</button>
                            <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                        </div>
                    </form>
                </div>
            </div>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
    
</html>
