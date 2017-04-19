/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Utils;

/**
 *
 * @author duclt
 */
public class ENV {

    public static final String SERVLET_LOGIN = "LoginServlet";
    public static final String SERVLET_SHOPPING = "ShoppingServlet";
    public static final String SERVLET_SHOPPING_ADD_TO_CART = "ShoppingAddServlet";
    public static final String SERVLET_SHOPPING_VIEW_CART = "ShoppingViewServlet";
    public static final String SERVLET_SHOPPING_CHECKOUT = "ShoppingCheckoutServlet";

    public static final String PAGE_LOGIN = "index.jsp";
    public static final String PAGE_SHOPPING_LIST = "ShoppingList.jsp";
    public static final String PAGE_SHOPPING_CART = "ShoppingCart.jsp";
    public static final String PAGE_ERROR = "errPage.jsp";

    public static final String DISPATCH = "Controller?btAction=";
    public static final String DISPATCH_SHOPPING_LIST = DISPATCH + "ShoppingList";
    public static final String DISPATCH_ADD_TO_CART = DISPATCH + "AddToCart&ProductID=";
    public static final String DISPATCH_VIEW_CART = DISPATCH + "ViewCart";
    public static final String DISPATCH_CHECKOUT = DISPATCH + "Checkout";

}
