package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import main.UserDataDTO;

public final class Myupdate_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    UserDataDTO ud = (UserDataDTO) s.getAttribute("login");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Myupdate</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"myupdateresult\" method=\"POST\">\n");
      out.write("            名前:\n");
      out.write("            <input type=\"text\" name=\"name\" value=\"");
      out.print( ud.getName());
      out.write("\">\n");
      out.write("            <br><br>\n");
      out.write("\n");
      out.write("            パスワード:\n");
      out.write("            <input type=\"text\" name=\"password\" value=\"");
      out.print( ud.getPassword());
      out.write(">\">\n");
      out.write("            <br><br>\n");
      out.write("\n");
      out.write("            メールアドレス:\n");
      out.write("            <input type=\"text\" name=\"mail\" value=\"");
      out.print( ud.getMail());
      out.write("\">\n");
      out.write("            <br><br>\n");
      out.write("\n");
      out.write("            住所\n");
      out.write("            <br>\n");
      out.write("            <textarea name=\"address\" rows=10 cols=50 style=\"resize:none\" wrap=\"hard\">");
      out.print( ud.getAddress());
      out.write("</textarea><br><br>\n");
      out.write("\n");
      out.write("            <input type=\"submit\" name=\"btnSubmit\" value=\"更新\"><br>\n");
      out.write("            <a href=\"./Top.jsp\">トップへ</a><br>\n");
      out.write("            </body>\n");
      out.write("        </form>\n");
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
