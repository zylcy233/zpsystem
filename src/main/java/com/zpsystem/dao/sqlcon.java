//package zpSystem.zyzpxt.dao;
//
//import org.apache.log4j.Logger;
//import zpSystem.zyzpxt.action.AdmServlet;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class sqlcon {
//    private static Connection 和数据建立连接(){
//        Logger logger=Logger.getLogger(String.valueOf(AdmServlet.class));
//        Connection connection=null;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            connection= DriverManager.getConnection("jdbc:oracle:thin:@192.168.13.192:1521:oracle","scott","123456");
//        } catch (Exception e) {
//            logger.debug(e.getMessage());
//        }
//        return connection;
//    }
//}
