package com.example.apigateway.config;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class AuthenticationFilter extends ZuulFilter {

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
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ctx.addZuulRequestHeader("username", username);
        return null;
    }
}