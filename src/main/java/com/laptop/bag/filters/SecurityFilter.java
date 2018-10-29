package com.laptop.bag.filters;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.laptop.bag.exception.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter,ContainerResponseFilter {
	
	private static final String BASIC_AUTH = "Basic ";
	private static final String AUTHORIZATION_KEY = "Authorization";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		boolean flag = false;
		if(requestContext.getUriInfo().getPath().contains("secure")){
			List<String> headers = requestContext.getHeaders().get(AUTHORIZATION_KEY);
			if(null == headers || !isAuthorize(Base64.decodeAsString(headers.get(0).replace(BASIC_AUTH, "")))){
				flag = true;
			}
			if(flag){
				ErrorMessage msg = new ErrorMessage(Status.UNAUTHORIZED, "An error occured");
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
						.entity(new GenericEntity<ErrorMessage>(msg, ErrorMessage.class))
						.build());
			}
			
		}
	}

	@Override
	public void filter(ContainerRequestContext arg0,
			ContainerResponseContext arg1) throws IOException {
	}
	
	private boolean isAuthorize(String password){
		String []str = password.split(":");
		if(str == null || str.length == 0) 
			return false;
		if(str[0].equalsIgnoreCase("admin") && str[1].equalsIgnoreCase("welcome"))
			return true;
		return false;
	}

}
