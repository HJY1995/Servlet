package student_demo;

import com.mysql.cj.api.x.Result;
import com.mysql.cj.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {
    static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://192.168.186.131:3306/test?useSSL=false";
    static final String user="root";
    static final String passwd="123456";

    public static int insert(String name, int age, String major, String hometown){
        //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //建立连接
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(DB_URL,user,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //创建执行SQL语句的 preparedStatement
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement= (PreparedStatement) connection.prepareStatement(
                    "INSERT INTO student (name, age, major, hometown) VALUES(?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int result= 0;
        try {
            assert preparedStatement != null;
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,major);
            preparedStatement.setString(4,hometown);

            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static Student search(int id){
        //注册JDBC驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //建立连接
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(DB_URL,user,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //创建执行SQL语句的 preparedStatement
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement= (PreparedStatement) connection.prepareStatement(
                    "select * from student where id=?");
            preparedStatement.setInt(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Student student=new Student();
        ResultSet resultSet=null;
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (!resultSet.next()) break;

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setId(resultSet.getInt("age"));
                student.setMajor(resultSet.getString("major"));
                student.setHometown(resultSet.getString("hometown"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return student;

    }

    public static void main(String[] args) {
//        insert("j2",18,"english","EN");
//        System.out.println(search(10).getName());
    }

}
