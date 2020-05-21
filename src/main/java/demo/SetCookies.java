package demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet("/setCookies")
public class SetCookies extends HttpServlet {
    /**
     * request param: name ,url
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie name=new Cookie("name",
                URLEncoder.encode(request.getParameter("name"),"UTF-8"));
        Cookie url=new Cookie("url",
                URLEncoder.encode(request.getParameter("url"),"UTF-8"));
        name.setMaxAge(60*60*24);
        url.setMaxAge(60*60*24);
        response.addCookie(name);
        response.addCookie(url);
        PrintWriter out=response.getWriter();
        out.println("设置cookie:\n name="+URLEncoder.encode(request.getParameter("name"),"UTF-8")+
                ";\n url="+ URLEncoder.encode(request.getParameter("url"),"UTF-8"));
    }
}
