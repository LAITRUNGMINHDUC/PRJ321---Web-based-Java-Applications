/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Utils;

import java.sql.Connection;
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
        DataSource ds = (DataSource) envCtx.lookup("JAVALAB");
        Connection con = ds.getConnection();
        if (con != null) {
            return con;
        } else {
            return null;
        }
    }
}
