package com.waxy.security;

import com.waxy.database.repository.UserRepository;
import com.waxy.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class.getSimpleName());
    @Autowired
    private UserRepository userRepository;

    /**
     * Intercept the all incoming requests (+)
     * Get JWT from the request (+)
     * Validate JWT (+)
     * Get the username from JWT and find the user with the username (+)
     * Forward the request to the next chain (+)
     */


    private final UserDetailsService myUserDetailsService;

    public AuthTokenFilter(UserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && JwtUtil.validateJwtToken(jwt)) {
                boolean isOk = true;
                String username = JwtUtil.getUsernameFromJwtToken(jwt);
                String requestPath = request.getServletPath();
                if(requestPath.contains("/userInfo/id/") ){
                    long userId = 0;
                    // Define the path variable identifier
                    String pathVariableIdentifier = "/userId/";
                    int startIndex = requestPath.lastIndexOf(pathVariableIdentifier);
                   if(startIndex != -1){
                       //Extract substring that contain the path variable
                       String subStringWithPathVariable = requestPath.substring(startIndex+pathVariableIdentifier.length());
                         userId = Long.parseLong(subStringWithPathVariable);
                   }

                    if(userId > 0 && !username.equals(userRepository.findUserNameById(userId))){
                        System.out.println("Toang user "+userRepository.findUserNameById(userId));
                        isOk = false;
                        return;
                    }
                }
                if(isOk){
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (Exception e) {
            logger.error("Exception: ", e);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return  request.getServletPath().equals("/api/login") ||
                request.getServletPath().equals("/api/register");
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}

