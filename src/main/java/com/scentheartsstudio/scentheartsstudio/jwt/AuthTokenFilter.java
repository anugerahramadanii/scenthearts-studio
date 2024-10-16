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

         String username = null;
         String jwtToken = null;

         if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
             jwtToken = requestTokenHeader.substring(7);
             try {
                 username = jwtTokenUtil.getUsernameFromToken(jwtToken);
             } catch (IllegalArgumentException e){
                 logger.warn("Unable to get JWT Token");
             } catch (ExpiredJwtException e) {
                 logger.warn("JWT Token has expired");
             } catch (Exception e) {
                logger.error("Error memproses JWT Token: " + e.getMessage());
             }
         } else {
             logger.warn("JWT Token does not begin with Bearer String");
         }

        // Once we get the token, validate it.
         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

             // If token is valid configure Spring Security to manually set authentication
             if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                 UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken
                         (userDetails, null, userDetails.getAuthorities());
                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 // After setting the Authentication in the context, we specify that the current user is authenticated.
                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    logger.warn("Token not valid for user: " + username);
                }
             }
            filterChain.doFilter(request, response);
         }
}