package com.zhiyou100.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 实现登陆的过滤 如果已经登录 则可以进入后台管理页面,如果没有登录 则进入登录界面
 */
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    /*具体的过滤逻辑的实现*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //判断用户当前是否是登录行为
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        if(requestURI.equals("/login/login.do")){
            //进入后续的过滤器 如果没有 则进入servlet请求
            chain.doFilter(request,response);
        }else{
            HttpSession session = req.getSession();
            Object user = session.getAttribute("user");
            //如果session中user为null,说明之前没有登录
            if(user==null){
                resp.sendRedirect("/login.html");
            }else{
                //说明用户之前已经登录 有权请求后续页面
                chain.doFilter(request,resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
