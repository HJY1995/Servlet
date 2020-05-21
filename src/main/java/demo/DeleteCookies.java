package demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteCookies")
public class DeleteCookies extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("name")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        out.println("del cookie:" + cookie.getName());
                    } else {
                        out.println("still alive cookie:" + cookie.getName());
                    }
                }
            } else {
                out.println("no cookies");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
