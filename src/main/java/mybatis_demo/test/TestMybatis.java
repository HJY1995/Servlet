package mybatis_demo.test;

import mybatis_demo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    public void test() throws IOException {
        String resource = "E:\\IdeaProjects\\webTest\\src\\main\\java\\resource\\sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Student> studentList = sqlSession.selectList("mybatis_demo.mapper.findAll");

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        new TestMybatis().test();
    }
}
