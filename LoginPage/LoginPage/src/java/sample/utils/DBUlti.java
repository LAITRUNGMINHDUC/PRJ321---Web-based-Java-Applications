/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TuanHDSE62146
 */
public class DBUlti implements Serializable {

    public static Connection makeConnection() throws /*ClassNotFoundException*/ SQLException, NamingException {
//        try {
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=IA1161;instanceName=TUANHDSE62146";
//            Connection con = DriverManager.getConnection(url, "sa", "hoangtuan357159");
//            return con;
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
        Context context = new InitialContext();
        Context tomcatCtx = (Context) context.lookup("java:comp/env");

        DataSource ds = (DataSource) tomcatCtx.lookup("JAVAWEB");
        Connection con = ds.getConnection();
        return con;
    }
}
