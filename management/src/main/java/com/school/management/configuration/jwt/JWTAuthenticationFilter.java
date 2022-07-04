package com.school.management.configuration.jwt;

import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;
    private JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserDetailsService userDetailsService, JWTTokenHelper jwtTokenHelper)
    {
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = jwtTokenHelper.getAuthHeaderFromHeader(request);

        if(authToken != null)
        {
            String userName;
            try {
                userName = jwtTokenHelper.getUsernameFromToken(authToken);
            } catch (JwtException ex) {
                throw new JwtException(String.format("Token %s cannot be trusted", authToken));
            }
            if(userName != null)
            {
                if(jwtTokenHelper.validateToken(authToken))
                {
                    Set<SimpleGrantedAuthority> grantedAuthorities = jwtTokenHelper.getGrantedAuthorities(authToken);
                    Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userName,
                            null,
                            grantedAuthorities
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
