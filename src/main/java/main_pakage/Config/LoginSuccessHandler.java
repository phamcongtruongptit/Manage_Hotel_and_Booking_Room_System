package main_pakage.Config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Autowired 
	private HttpSession httpSession ; 
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		Set<String> rolesOfUser = new HashSet<>() ; 
		userDetails.getAuthorities().forEach(item -> rolesOfUser.add(item.toString()));
		httpSession.setAttribute("usernameLogined", userDetails.getUsername());
		if(rolesOfUser.contains("MANAGER")) {
			response.sendRedirect("/manager");
			return ; 
		} else if(rolesOfUser.contains("SELLER")) {
			response.sendRedirect("/seller") ; 
			return ; 
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
