package com.example.apigateway.config;

import com.example.apigateway.Bean.UserBean;
import com.example.apigateway.Repository.UserRepository;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    UserRepository userRepository;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }


    @Override
    public boolean shouldFilter() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username !="anonymousUser") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserBean user = null;

        if (userRepository.findByUsername(
                username) !=null) {
            user = userRepository.findByUsername(username);
            ctx.addZuulRequestHeader("username", username);
        }

        return null;
    }
}