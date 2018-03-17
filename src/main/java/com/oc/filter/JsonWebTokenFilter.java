package com.oc.filter;

import com.oc.utils.jwt.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonWebTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_REQUEST_HEAD = "AUTH_TOKEN";

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String token = httpServletRequest.getHeader(AUTH_REQUEST_HEAD);

//        if (token != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
            SecurityContext securityContext = SecurityContextHolder.getContext();
            System.out.println(securityContext.getAuthentication().getName());

            if (userDetails != null && (securityContext = SecurityContextHolder.getContext()).getAuthentication() == null) {
                System.out.println(userDetails.getUsername());
                //UsernamePasswordAuthenticationToken :
                // 参数1：principal（安全认证信息类,即JwtPrincipal）2: 3:角色权限信息authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        httpServletRequest));
                securityContext.setAuthentication(authentication);
            }
//        }

        super.doFilter(httpServletRequest, httpServletResponse, filterChain);
    }

}
