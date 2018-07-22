<%@page import="kagoyume.KagoyumeHelper"%>
<%-- 
    Document   : error
    Created on : 2018/07/09, 17:19:27
    Author     : sumi3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%><br>
        <%=KagoyumeHelper.getInstance().home()%>
    </body>
</html>
