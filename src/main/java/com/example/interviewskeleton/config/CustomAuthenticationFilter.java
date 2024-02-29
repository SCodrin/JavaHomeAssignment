package com.example.interviewskeleton.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {
		
		private static final String AUTH_TOKEN = "such-secure-much-wow";
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
				String requestURI = request.getRequestURI();
				log.info("Request URI is: {}", requestURI);
				
				if (request.getRequestURI().contains("/api/v1/greeting/greet")) {
						String authToken = request.getHeader("X-Auth-Token");
						log.info("Auth Token found: {}", authToken);
						
						if (AUTH_TOKEN.equals(authToken)) {
								Authentication auth = new UsernamePasswordAuthenticationToken("user", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
								SecurityContextHolder.getContext().setAuthentication(auth);
								filterChain.doFilter(request, response); // Proceed with the request
						} else {
								log.warn("Invalid or missing authentication token");
								response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or missing authentication token"); // Block the request
						}
				} else {
						filterChain.doFilter(request, response); // Not targeting /greet, proceed without checking
				}
		}
		
}