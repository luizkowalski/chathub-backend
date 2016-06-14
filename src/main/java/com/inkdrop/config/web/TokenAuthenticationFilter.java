package com.inkdrop.config.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.inkdrop.app.domain.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns={"/v1/*", "/v1/**"})
@Slf4j
public class TokenAuthenticationFilter implements Filter {

	@Autowired
	UserRepository userRepository;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(httpRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpRequest.getMethod())){
			chain.doFilter(httpRequest, response);
			return;
		}
		
		String backendToken = httpRequest.getHeader("Auth-Token");
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		if(backendToken == null){
			log.info("Token is null");
			servletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "No token given");
			return;
		}
		
		if(userRepository.findByBackendAccessToken(backendToken) == null){
			log.info("Invalid token: "+backendToken);
			servletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
			return;
		} 

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}