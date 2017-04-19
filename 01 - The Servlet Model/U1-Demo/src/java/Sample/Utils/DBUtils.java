/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sample.Utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author duclt
 */
public class DBUtils implements Serializable{
    public static Connection makeConnection()
        throws ClassNotFoundException, SQLException {      
        //1. Khai báo Class
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //2. Protocol đến SQL Server
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JAVAWEB";        
        
        //3. Tạo Connection
        Connection c = DriverManager.getConnection(url, "sa", "123456789");        
        
        //4. Trả KQ
        return c;        
    }    
}
