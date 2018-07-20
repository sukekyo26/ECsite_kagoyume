package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.TopError;
import kagoyume.UserDataDTO;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

    TopError te = (TopError)request.getAttribute("TopError");
    UserDataDTO udd = (UserDataDTO)session.getAttribute("loginUserData");

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"https://use.fontawesome.com/releases/v5.0.6/css/all.css\" rel=\"stylesheet\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("        <title>かごゆめ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"header\">\n");
      out.write("        <nav class=\"navbar navbar-default\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navBootstrap\">\n");
      out.write("                        <span class=\"sr-only\">メニュー</span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                    <a href=\"top.jsp\" class=\"navbar-brand\" style=\"font-size: 150%\">かごゆめ</a>\n");
      out.write("                </div>                \n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"navBootstrap\">\n");
      out.write("                    ");
if(udd != null){
      out.write("\n");
      out.write("                    <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                        <li><p class=\"navbar-text\">ようこそ<a href=\"MyData\"><i class=\"far fa-address-card\"></i>");
      out.print(udd.getName());
      out.write("</a>さん!</p></li> \n");
      out.write("                        <li><a href=\"Cart\"><i class=\"fas fa-shopping-cart\"></i> 買い物かご</a></li>\n");
      out.write("                        <li><a href=\"Login?set=logout\"><strong><i class=\"fas fa-sign-out-alt\"></i> ログアウト</strong></a></li>    \n");
      out.write("                    </ul>\n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                    <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                        <li><a href=\"Cart\"><i class=\"fas fa-shopping-cart\"></i> 買い物かご</a></li>\n");
      out.write("                        <li><a href=\"Login?previous=top\"><strong><i class=\"fas fa-sign-in-alt\"></i> ログイン</strong></a></li>\n");
      out.write("                    </ul>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        </header>\n");
      out.write("        \n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("            <h1>\n");
      out.write("                \"あなたが望む商品を購入可能です。\"<br>\n");
      out.write("                \"購入代金は一切かかりません。\"<br>\n");
      out.write("                \"夢のショッピングサイトへようこそ。\"<br>\n");
      out.write("            </h1>\n");
      out.write("            <div class=\"panel panel-success\">\n");
      out.write("                <div class=\"panel-body panel-success\">\n");
      out.write("                <h1 class=\"text-center\">商品検索</h1>\n");
      out.write("                <br>\n");
      out.write("                <form action=\"Search\" method=\"GET\" class=\"form-horizontal\">\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <label class=\"col-sm-3 control-label\" for=\"keywd\">キーワード検索(必須)：</label>\n");
      out.write("                        <div class=\"col-sm-8\">\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"keywd\" name=\"search\" value=\"");
if(te != null && te.getKeyword() != null){out.print(te.getKeyword());}
      out.write("\"><br>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <label class=\"col-sm-3 control-label\" for=\"category\">カテゴリ検索：</label>\n");
      out.write("                        <div class=\"col-sm-8\">\n");
      out.write("                            <select name=\"category_id\" class=\"form-control\" id=\"category\">\n");
      out.write("                                <option value=\"1\" selected>すべてのカテゴリ</option>\n");
      out.write("                                <option value=\"13457\">ファッション</option>\n");
      out.write("                                <option value=\"2498\">食品</option>\n");
      out.write("                                <option value=\"2513\">アウトドア、釣り、旅行用品</option>\n");
      out.write("                                <option value=\"2500\">ダイエット、健康</option>\n");
      out.write("                                <option value=\"2501\">コスメ、美容、ヘアケア</option>\n");
      out.write("                                <option value=\"2502\">スマホ、タブレット、パソコン</option>\n");
      out.write("                                <option value=\"2504\">テレビ、オーディオ、カメラ</option>\n");
      out.write("                                <option value=\"2505\">家電</option>\n");
      out.write("                                <option value=\"2506\">家具、インテリア</option>\n");
      out.write("                                <option value=\"2507\">花、ガーデニング</option>\n");
      out.write("                                <option value=\"2508\">キッチン、日用品、文具</option>\n");
      out.write("                                <option value=\"2503\">DIY、工具、ガーデニング</option>\n");
      out.write("                                <option value=\"2509\">ペット用品、生き物</option>\n");
      out.write("                                <option value=\"2510\">楽器、手芸、コレクション</option>\n");
      out.write("                                <option value=\"2511\">ゲーム、おもちゃ</option>\n");
      out.write("                                <option value=\"2497\">ベビー、キッズ、マタニティ</option>\n");
      out.write("                                <option value=\"2512\">スポーツ</option>\n");
      out.write("                                <option value=\"2514\">車、バイク、自転車</option>\n");
      out.write("                                <option value=\"2516\">CD、音楽ソフト</option>\n");
      out.write("                                <option value=\"2517\">DVD、映像ソフト</option>\n");
      out.write("                                <option value=\"10002\">本、雑誌、コミック</option>\n");
      out.write("                                <option value=\"47727\">レンタル、各種サービス</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <label class=\"col-sm-3 control-label\" for=\"sort\">ソート順：</label>\n");
      out.write("                        <div class=\"col-sm-8\">\n");
      out.write("                            <select name=\"sort\" class=\"form-control\" id=\"sort\">\n");
      out.write("                                <option value=\"-score\" selected>おすすめ順(デフォルト)</option>\n");
      out.write("                                <option value=\"+price\">価格安い順</option>\n");
      out.write("                                <option value=\"-price\">価格高い順</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <label class=\"col-sm-3 control-label\" for=\"highPrice\">最高金額指定(未記入可)：</label>\n");
      out.write("                        <div class=\"col-sm-8\">\n");
      out.write("                            <input type=\"number\" class=\"form-control\" id=\"highPrice\" name=\"highPrice\" min=\"1\">\n");
      out.write("                        </div>\n");
      out.write("                        <label class=\"col-sm-1 control-label\" style=\"right: 60px\" for=\"lowPrice\">円</label>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <label class=\"col-sm-3 control-label\" for=\"lowPrice\">最低金額指定(未記入可)：</label>\n");
      out.write("                        <div class=\"col-sm-8\">\n");
      out.write("                            <input type=\"number\" class=\"form-control\" id=\"lowPrice\" name=\"lowPrice\" min=\"1\">\n");
      out.write("                        </div>\n");
      out.write("                        <label class=\"col-sm-1 control-label\" style=\"right: 60px\" for=\"lowPrice\">円</label>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group container\">\n");
      out.write("                        <div class=\"col-sm-offset-3 col-sm-9\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-success\">検索</button>\n");
      out.write("                            ");
if(te != null && !te.getMarkCheck()){
      out.write("<span style=\"color: red\">");
out.print("＊検索キーワードを入力してください＊");
      out.write("</span>");
}
      out.write("  \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
