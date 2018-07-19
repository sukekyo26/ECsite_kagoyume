<%@page import="kagoyume.UserData"
        import="kagoyume.KagoyumeHelper"%>
<%
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
        <title>会員登録</title>
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
            <div class="panel panel-success">
                <div class="panel-body">
                    <h1 class="text-center">新規会員登録</h1>
                    <form action="RegistrationConfirm" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="user">ユーザー名：</label>
                            <div class="col-sm-10">
                                <input type="text" name="user" value="<%if(ud != null){out.print(ud.getName());}%>" class="form-control" id="user">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="pass">パスワード：</label>
                            <div class="col-sm-10">
                                <input type="text" name="password" value="<%if(ud != null){out.print(ud.getPassword());}%>" class="form-control" id="pass">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="mail">メールアドレス：</label>
                            <div class="col-sm-10">
                                <input type="email" name="mail" value="<%if(ud != null){out.print(ud.getMail());}%>" class="form-control" id="mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="address">住所：</label>
                            <div class="col-sm-10">
                                <input type="text" name="address-level1" placeholder="都道府県" value="<%if(ud != null){out.print(ud.getAddressLevel1());}%>" class="form-control" id="address">
                                <input type="text" name="address-level2" placeholder="市区町村" value="<%if(ud != null){out.print(ud.getAddressLevel2());}%>" class="form-control" id="address">
                                <input type="text" name="address-line1" placeholder="番地等" value="<%if(ud != null){out.print(ud.getAddressLine1());}%>" class="form-control" id="address">
                                <input type="text" name="address-line2" placeholder="アパート/マンション名等" value="<%if(ud != null){out.print(ud.getAddressLine2());}%>" class="form-control" id="address">
                            </div>
                        </div>
                        <div class="col-sm-offset-2 col-sm-10">
                            <%if(ud != null && !ud.getAddressCheck()){%><p style="color: red"><%out.print("＊住所の値が不正です＊");%></p><%}%>
                            <%if(ud != null && !ud.getMarkCheck()){%><p style="color: red"><%out.print("＊未記入の項目を入力してください＊");%></p><%}%>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">登録</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%=KagoyumeHelper.getInstance().home()%>
        </div>
    </body>
</html>
