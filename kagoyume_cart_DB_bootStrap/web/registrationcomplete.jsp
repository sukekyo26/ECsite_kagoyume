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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>登録完了</h1>
        <p>ユーザー名：<%=ud.getName()%></p>
        <p>パスワード：<%=ud.getPassword()%></p>
        <p>メールアドレス：<%=ud.getMail()%></p>
        <p>住所：<%=ud.getAddress()%></p>
        <br>
        <h4>上記の内容で登録しました。</h4>
        <br>
        <font size="5"><%=KagoyumeHelper.getInstance().home()%></font>
    </body>
</html>
