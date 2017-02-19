package com.rthings.ConnectEdAuthModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.rthings.ConnectEdAuthModule.principals.RolePrincipal;
import com.rthings.ConnectEdAuthModule.principals.UserPrincipal;

public class ConnectedLoginModule implements LoginModule {

	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private  RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroup;
	
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
			handler=callbackHandler;
			this.subject=subject;
			
	}

	@Override
	public boolean login() throws LoginException {
		javax.security.auth.callback.Callback[] callbacks = new javax.security.auth.callback.Callback[2];
	    callbacks[0] = new NameCallback("login");
	    callbacks[1] = new PasswordCallback("password", true);

	    try {
	      handler.handle(callbacks);
	      String name = ((NameCallback) callbacks[0]).getName();
	      String password = String.valueOf(((PasswordCallback) callbacks[1])
	          .getPassword());

	      if (name != null &&
	          name.equals("user123") &&
	          password != null &&
	          password.equals("pass123")) {

	        login = name;
	        userGroup = new ArrayList<String>();
	        userGroup.add("admin");
	        return true;
	      }

	      // If credentials are NOT OK we throw a LoginException
	      throw new LoginException("Authentication failed");

	    } catch (IOException e) {
	      throw new LoginException(e.getMessage());
	    } catch (UnsupportedCallbackException e) {
	      throw new LoginException(e.getMessage());
	    }

		
	}

	@Override
	  public boolean commit() throws LoginException {

	    userPrincipal = new UserPrincipal(login);
	    subject.getPrincipals().add(userPrincipal);
	    
	    if (userGroup != null && userGroup.size() > 0) {
	      for (String groupName : userGroup) {
	        rolePrincipal = new RolePrincipal(groupName);
	        subject.getPrincipals().add(rolePrincipal);
	        
	        
	      }
	    }

	    return true;
	  }

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
	    subject.getPrincipals().remove(rolePrincipal);
	    return true;
	}

	
}
