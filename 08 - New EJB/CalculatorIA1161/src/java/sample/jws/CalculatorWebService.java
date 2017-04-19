/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.jws;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import sample.calculator.CalculatorSessionBeanLocal;

/**
 *
 * @author TuanHDSE62146
 */
@WebService(serviceName = "CalculatorWebService")
@Stateless()
public class CalculatorWebService {

    @EJB
    private CalculatorSessionBeanLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "addNumber")
    public double addNumber(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        return ejbRef.addNumber(num1, num2);
    }

    @WebMethod(operationName = "subNumber")
    public double subNumber(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        return ejbRef.subNumber(num1, num2);
    }
    
}
