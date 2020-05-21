package demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/refresh")
public class Refresh extends HttpServlet {
    int count;

    public void init() {
        count = 0;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        count++;
        response.setIntHeader("Refresh", 2);
        PrintWriter out = response.getWriter();
        out.println("每2s自动刷新页面，页面点击次数：" + count);
    }
}
