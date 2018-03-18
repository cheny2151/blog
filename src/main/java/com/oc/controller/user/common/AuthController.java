package com.oc.controller.user.common;

import com.oc.utils.jwt.JwtPrincipal;
import com.oc.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("authController")
@RequestMapping("/auth")
public class AuthController {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        JwtPrincipal jwtPrincipal = (JwtPrincipal) userDetailsService.loadUserByUsername(username);
        if (jwtPrincipal != null) {
            return JwtUtils.generateToken(jwtPrincipal);
        }
        return null;
    }

}
