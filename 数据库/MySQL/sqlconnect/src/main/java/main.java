
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {
    public static void main(String[] args){

        String driverName = "com.mysql.cj.jdbc.Driver";
        String dbURL="jdbc:mysql://localhost:3306/world";  //world为数据库名
        String userName = "root";
        String userPwd = "219admin";
        Connection con = null;
        try {
            Class.forName(driverName);
            con= DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");
            Statement stmt = con.createStatement();
            String sql = "select Code, Name, Population from country";
            ResultSet resSet = stmt.executeQuery(sql);
            while(resSet.next()) {
                String code = resSet.getString("Code");
                String Name = resSet.getString("Name");
                String countryCode = resSet.getString("Population");

                System.out.println(code + "\t" + Name + "\t" + countryCode);
            }
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");
        }
    }
}
