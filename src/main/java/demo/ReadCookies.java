package demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/readCookies")
public class ReadCookies extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    out.println(cookie.getName() + "=" + cookie.getValue());
                }
            } else {
                out.println("no cookies");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
