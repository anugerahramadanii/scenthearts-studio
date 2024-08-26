package com.scentheartsstudio.scentheartsstudio.jwt;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

         final String requestTokenHeader = request.getHeader("Authorization");

         String email = null;
         String jwtToken = null;

         if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
             jwtToken = requestTokenHeader.substring(7);
             try {
                 email = jwtTokenUtil.getUsernameFromToken(jwtToken);
             } catch (ExpiredJwtException e) {
                 logger.warn("JWT Token has expired");
             } catch (Exception e) {
                 logger.error("Unable to get JWT Token");
             }
         } else {
             logger.warn("JWT Token does not begin with Bearer String");
         }

         if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(email);
             if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                         (userDetails, null, userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
             }
            filterChain.doFilter(request, response);
         }
}