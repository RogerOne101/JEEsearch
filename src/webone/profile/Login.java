package webone.profile;

import java.io.IOException;
import java.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import utils.WebSecurity;

@WebServlet("/profile/Login")
public final class Login extends HttpServlet {

  @EJB
  private ProfileManager pm;

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    String login = request.getParameter("login");
    String passwd = request.getParameter("passwd");
    
    Profile profile = pm.findProfile(login, WebSecurity.MD5(passwd));

    if (profile != null) {
    	
    	HttpSession sess = request.getSession(true);
    	
    	if (profile instanceof Admin) {
    		sess.setAttribute("IS-ADMIN", Boolean.TRUE);
    	} else {
    		sess.setAttribute("IS-ADMIN", Boolean.FALSE);
    	}
    	
    	sess.setAttribute("LOGGED-IN", Boolean.TRUE);
    	sess.setAttribute("LOGIN", login);
    	response.sendRedirect("../protected.jsp");
    	return;
    }

    response.sendRedirect("loginform.jsp");
  }

}
