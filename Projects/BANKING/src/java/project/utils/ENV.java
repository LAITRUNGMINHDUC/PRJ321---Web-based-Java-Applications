/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.utils;

/**
 *
 * @author duclt
 */
public class ENV {

    //1. Login Zone
    public static final String PAGE_LOGIN = "login.html";
    public static final String PAGE_ERRORLOGIN = "errLogin.html";
    public static final String PAGE_ERRORLOCK = "errLockAccount.html";
    
    //2. Transaction Zone
    public static final String PAGE_TRANSACTION = "transaction.jsp";
    public static final String PAGE_PRINT = "printTransaction.jsp";
    public static final String PAGE_TRANSFER = "transfer.jsp";

    //3. Servlet
    public static final String SERVLET_LOGIN = "LoginServlet";
    public static final String SERVLET_TRANSACTION = "TransactionServlet";

    public static final String SERVLET_TRANSFER = "TransferServlet";
    public static final String SERVLET_PROCESS_TRANSFER = "ProcessTransferServlet";

    public static final String SERVLET_PRINT_TRANSACTION = "PrintTransactionServlet";
    public static final String SERVLET_HIDE_TRANSACTION = "HideTransactionServlet";

    public static final String SERVLET_LOGOUT = "LogoutServlet";

    //4. Dispatcher
    public static final String DISPATCHER = "Controller?btAction=";

    //5. Error
    public static final String PAGE_ERROR = "errPage.jsp";

}
