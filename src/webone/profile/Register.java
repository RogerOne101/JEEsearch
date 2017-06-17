package webone.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.WebSecurity;

@WebServlet("/profile/Register")
public final class Register extends HttpServlet {
  
  @EJB
  private ProfileManager pm;

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String login  = request.getParameter("login");
    String passwd = request.getParameter("passwd");
    String email = request.getParameter("email");
    
    Profile profile = pm.register(login, WebSecurity.MD5(passwd), email);
    if(profile == null) {
      response.sendRedirect("register.jsp");
      return;
    }
    
    pm.updateLoginList();
    
    HttpSession sess = request.getSession(true);
    sess.setAttribute("LOGGED-IN", Boolean.TRUE);
    sess.setAttribute("LOGIN", login);
    sess.setAttribute("IS-ADMIN", Boolean.FALSE);
    response.sendRedirect("../protected.jsp");
  }

}
