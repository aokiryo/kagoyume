package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import main.UserDataDTO;

public final class Top_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");

    HttpSession s = request.getSession();
    UserDataDTO login = null;
    if (s.getAttribute("login") != null) {
        login = (UserDataDTO) s.getAttribute("login");
    }

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>top page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>かごゆめTOP</h1><br>\n");
      out.write("        <h3>このサイトはYahooショッピングで\n");
      out.write("            好きなだけ買い物した気分になれるサイトです</h3><br><br>\n");
      out.write("\n");
      out.write("        ");
if (s.getAttribute("login") != null) {
      out.write("\n");
      out.write("        ようこそ");
      out.print( login.getName());
      out.write("さん<br><br>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("\n");
      out.write("        <form action=\"search\" method=\"get\">\n");
      out.write("            商品検索 : <input type=\"text\" name=\"search\">\n");
      out.write("            <input type=\"submit\" name=\"btnSubmit\" value=\"検索\">\n");
      out.write("        </form>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
s.setAttribute("URL", "http://localhost:8080/Java_EC/Top.jsp");
      out.write("\n");
      out.write("        ");
if (s.getAttribute("login") != null) {
      out.write("\n");
      out.write("        <a href=\"login\">ログアウト</a><br>\n");
      out.write("        <a href=\"cart\">カート</a><br>\n");
      out.write("        <a href=\"mydata\">マイデータ</a><br>\n");
      out.write("        ");
} else {
      out.write("\n");
      out.write("        <a href=\"login\">ログイン</a><br>\n");
      out.write("        ");
}
      out.write("\n");
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
