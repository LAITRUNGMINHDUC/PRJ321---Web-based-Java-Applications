/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.calculator;

import javax.ejb.Local;

/**
 *
 * @author TuanHDSE62146
 */
@Local
public interface CalculatorSessionBeanLocal {

    double addNumber(double num1, double num2);

    double subNumber(double num1, double num2);
    
}
