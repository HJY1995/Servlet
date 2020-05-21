package demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(filterName = "提交过滤器",urlPatterns = "/*",
//        initParams = {@WebInitParam(name = "submit",value = "123456")})

public class SubmitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
        String initValue=filterConfig.getInitParameter("submit");
        System.out.println("initValue = "+initValue);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器开始执行，如果name=baidu，则过滤");

        try {
            if ((servletRequest.getParameter("name")).equals("baidu")){
                servletResponse.setContentType("text/html;charset=UTF-8");
                PrintWriter out=servletResponse.getWriter();
                String title = "过滤baidu";
                String docType = "<!DOCTYPE html> \n";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "</body></html>");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        } catch (IOException e) {
            PrintWriter out=servletResponse.getWriter();
            out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
