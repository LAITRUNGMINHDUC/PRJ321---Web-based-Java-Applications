/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.calculator;

import javax.ejb.Stateless;

/**
 *
 * @author TuanHDSE62146
 */
@Stateless
public class CalculatorSessionBean implements CalculatorSessionBeanLocal, CalculatorSessionBeanRemote {
    
    @Override
    public double addNumber(double num1, double num2) {
        return num1 + num2;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public double subNumber(double num1, double num2) {
        return num1 - num2;
    }
}
