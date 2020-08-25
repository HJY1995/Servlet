package student_demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/msg")
public class PostMsg extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //中文转码
        int id=Integer.parseInt(request.getParameter("id"));
        String msg=request.getParameter("msg");
        Student student= null;
        try {
            student=StudentDAO.search(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(student!=null){
            String name=student.getName();

           out.println("<!DOCTYPE html>\n" +
                   "<html>\n" +
                   "<head>\n" +
                   "\t<meta charset=\"UTF-8\">\n" +
                   "</head>\n" +
                   "<body bgcolor=\\\"#f0f0f0\\\">\n" +
                   "<h1 align=\\\"center\\\"> 学生发送信息 </h1>\n" +
                   "<ul>\n" +
                   "\t<li><b>发送人学号</b>："+id+"\n" +
                   "\t<li><b>发送人姓名</b>："+name+"\n" +
                   "\t<li><b>发送消息</b>："+msg+"\n" +
                   "</ul>\n" +
                   "</body>\n" +
                   "</html>");

        }else{
            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body id=\"body\">\n" +
                    "<h2>不存在此学生</h2>\n" +
                    "</body>\n" +
                    "</html>");
        }



    }

}
