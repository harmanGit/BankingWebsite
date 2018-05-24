package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class MenuJsp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Menu Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <center>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Your Accounts:</p>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input name=\"action\" type=\"submit\" value=\"Check All Accounts\">\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <hr style=\"width: 60%\"/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Withdraw:</p>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <select name=\"Accounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            </select>\n");
      out.write("            <input name=\"wMoney\" type=\"number\" placeholder=\"$$$\" required>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input name=\"action\" type=\"submit\" value=\"Withdraw\">\n");
      out.write("        </form>\n");
      out.write("        <br/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <hr style=\"width: 60%\"/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Deposit:</p>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <select name=\"Accounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            </select>\n");
      out.write("            <input name=\"dMoney\" type=\"number\" placeholder=\"$$$\" required>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input name=\"action\" type=\"submit\" value=\"Deposit\">\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <hr style=\"width: 60%\"/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Transfer:</p>\n");
      out.write("            <input name=\"tMoney\" type=\"number\" placeholder=\"$$$\" required>\n");
      out.write("            <br> <br>\n");
      out.write("            ");
      out.write("\n");
      out.write("            From what Account-->\n");
      out.write("            <select name=\"FAccounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            ");
      out.write("\n");
      out.write("            </select>\n");
      out.write("            To what Account-->\n");
      out.write("            <select name=\"TAccounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            </select>\n");
      out.write("            <br>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input style=\"margin-top: 15px\" name=\"action\" type=\"submit\" value=\"Transfer\" required>\n");
      out.write("        </form>\n");
      out.write("        <br/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <hr style=\"width: 70%\"/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Check Transactions:</p>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <select name=\"TrAccounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            </select>\n");
      out.write("            <br><br>\n");
      out.write("             ");
      out.write("\n");
      out.write("            From Date -->\n");
      out.write("            <input style=\"margin-right: 10px\" name=\"fDate\" type=\"text\" placeholder=\"YYYY-MM-DD\" required>\n");
      out.write("             ");
      out.write("\n");
      out.write("            To Date -->\n");
      out.write("            <input name=\"tDate\" type=\"text\" placeholder=\"YYYY-MM-DD\" required>\n");
      out.write("            <br><br>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input name=\"action\" type=\"submit\" value=\"Transactions\">\n");
      out.write("        </form>\n");
      out.write("        <br/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <hr style=\"width: 60%\"/>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <p>Open New Account:</p>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <select name=\"Accounts\" size=\"1\">\n");
      out.write("                <option>Checking</option>\n");
      out.write("                <option>Saving</option>\n");
      out.write("                <option>Money Market</option>\n");
      out.write("            </select>\n");
      out.write("             ");
      out.write("\n");
      out.write("            <input style=\"margin-left: 10px\" name=\"action\" type=\"submit\" value=\"New Account\">\n");
      out.write("        </form> \n");
      out.write("        <br>\n");
      out.write("         ");
      out.write("\n");
      out.write("        <form action=\"/finalProjectJAVA3/Controller\" method=\"post\">\n");
      out.write("            <input name=\"action\" type=\"submit\" value=\"Logout\">\n");
      out.write("        </form>\n");
      out.write("    </center>\n");
      out.write("</body>\n");
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
