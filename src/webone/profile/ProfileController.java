package webone.profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ProfileController extends HttpServlet {
        
	private static final long serialVersionUID = 1L;
	
	@EJB
	  private ProfileManager pm;

    @Override
	protected void doGet(HttpServletRequest request,
    	HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("application/json");
    	try {
    		String term = request.getParameter("term");
            System.out.println("Data from ajax call " + term);
                        
            Profile profile = pm.findProfile(term);

            String profileJson = new Gson().toJson(profile);
            System.out.println("profileJson: "+profileJson);
            response.getWriter().write(profileJson);
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        }
    }
}