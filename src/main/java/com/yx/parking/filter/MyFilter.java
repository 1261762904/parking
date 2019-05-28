package com.yx.parking.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yixin
 * @create 2019-05-12 11:37
 */

@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String servletPath = httpRequest.getServletPath();
        if(servletPath.endsWith(".js") || servletPath.endsWith(".css") || servletPath.endsWith(".map")
                || servletPath.endsWith(".jpg") || servletPath.endsWith(".jpeg")
                || servletPath.endsWith(".gif") || servletPath.endsWith(".bmp")
                || servletPath.endsWith(".png") || servletPath.endsWith(".ico")
        )
        {
            filterChain.doFilter(request, response);
            return;
        }
        if(servletPath.equals("/")||servletPath.equals("/index.html")){
            filterChain.doFilter(request, response);
            return;
        }
        if(servletPath.startsWith("/parking"))
        {
            filterChain.doFilter(request, response);
            return;
        }
        Cookie[] cs = httpRequest.getCookies();
        if(cs!=null){
            for (int i = 0; i < cs.length; i++) {
                if ("aId".equals(cs[i].getName())&&!cs[i].getValue().equals("")) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        httpResponse.sendRedirect("/");
    }

    @Override
    public void destroy() {

    }
}
