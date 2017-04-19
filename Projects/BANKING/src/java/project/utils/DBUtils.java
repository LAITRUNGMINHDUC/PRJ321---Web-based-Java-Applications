/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static Connection makeConnection() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("JAVAPROJECT");
        Connection c = ds.getConnection();

        if (c != null) {
            System.out.println("Connection OK");
            return c;
        } else {
            System.out.println("Cannot Connect");
            return null;
        }
    }

    public static void closeConnection(Connection c, PreparedStatement stm, ResultSet rs)
            throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (c != null) {
            c.close();
        }
    }

}
