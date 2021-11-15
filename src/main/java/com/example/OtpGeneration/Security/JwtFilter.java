package com.example.OtpGeneration.Security;

import com.example.OtpGeneration.ServiceImpl.ApiServiceImpl;
import com.example.OtpGeneration.Utils.JwtUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    JwtUtils jwtUtils = new JwtUtils();

    @Autowired
    ApiServiceImpl apiServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = jwtUtils.getJwtFromRequest(request);

            if (StringUtils.hasText(token) && jwtUtils.validateToken(token, "secrets")) {
                String email = jwtUtils.UserNameFromJWTs(token, "secrets");

                if (Strings.isNullOrEmpty(email)) {
                    request.getRequestDispatcher("/error" + "invalid")
                            .forward(request, response);
                }

                UserDetails userDetails = apiServiceImpl.loadByEmail(email);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
           request.getRequestDispatcher("/error" + e.getMessage()).forward(request,response);
        }

    }
}
