/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author duclt
 */
public class DBUtils {

    public static Connection makeConnection()
            throws /*ClassNotFoundException,*/ SQLException, NamingException {

        Context context = new InitialContext();

        //Context phía server
        Context tomcatCtx = (Context) context.lookup("java:comp/env");

        DataSource ds = (DataSource) tomcatCtx.lookup("JAVAWEB");

        Connection con = ds.getConnection();

        return con;

//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=JAVAWEB";
//        Connection c = DriverManager.getConnection(url, "sa", "123456789");
//        return c;
    }
}
