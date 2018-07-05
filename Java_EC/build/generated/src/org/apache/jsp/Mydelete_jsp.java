package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import main.UserDataDTO;

public final class Mydelete_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write('\n');

    HttpSession s = request.getSession();
    UserDataDTO dto = (UserDataDTO) s.getAttribute("userData");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Mydelete</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>あなたの登録情報です</h1><br>\n");
      out.write("        名前:");
      out.print( dto.getName());
      out.write("<br>\n");
      out.write("        パスワード:");
      out.print( dto.getPassword());
      out.write("<br>\n");
      out.write("        メールアドレス:");
      out.print( dto.getMail());
      out.write("<br>\n");
      out.write("        住所:");
      out.print( dto.getAddress());
      out.write("<br>\n");
      out.write("        総購入金額:");
      out.print( dto.getTotal());
      out.write("<br>\n");
      out.write("        最終更新日時:");
      out.print( dto.getNewDate());
      out.write("<br><br>\n");
      out.write("        <h1>本当に削除してもよろしいですか？</h1><br>\n");
      out.write("        <a href=\"mydeleteresult\">はい</a><br>\n");
      out.write("        <a href=\"./Top.jsp\">いいえ（トップへ）</a><br>\n");
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
