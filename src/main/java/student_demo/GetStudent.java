package student_demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info")
public class GetStudent extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //中文转码
        int id=Integer.parseInt(request.getParameter("id"));

        Student student= null;
        try {
            student=StudentDAO.search(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(student!=null){
            String name=student.getName();
            int age=student.getAge();
            String major=student.getMajor();
            String hometown=student.getHometown();

            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body bgcolor=\\\"#f0f0f0\\\">\n" +
                    "<h1 align=\\\"center\\\"> 查询学生信息 </h1>\n" +
                    "<ul>\n" +
                    "\t<li><b>学号</b>："+id+"\n" +
                    "\t<li><b>姓名</b>："+name+"\n" +
                    "\t<li><b>年龄</b>："+age+"\n" +
                    "\t<li><b>专业</b>："+major+"\n" +
                    "\t<li><b>家乡</b>："+hometown+"\n" +
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
                    "<h2>不存在此学生信息</h2>\n" +
                    "</body>\n" +
                    "</html>");
        }



    }

}
