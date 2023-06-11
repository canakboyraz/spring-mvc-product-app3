package com.works.config;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final TinkEncDec tinkEncDec;
    final CustomerService customerService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String freeUrls[] = {"/","/register","/registerUser","/dashboard/login","/dashboard/loginUser"};
        boolean loginStatus = true;
        for( String item : freeUrls ){
            if(url.equals(item)){
                loginStatus=false;
                break;
            }
        }

        if(loginStatus){
            //session control
            boolean status = req.getSession().getAttribute("customer") == null;
            if( status ) {
                // redirect Login
                res.sendRedirect("/dashboard/login");
            }else {
                Customer c =(Customer) req.getSession().getAttribute("customer");
                req.setAttribute("user",c);
                filterChain.doFilter(req,res);
            }
        }else {
            filterChain.doFilter(req,res);
        }
    }
}
