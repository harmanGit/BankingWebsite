/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.io.Serializable;

/**
 * Java class used as a bean to store the table for the balance when checking
 * the menu.
 *
 * @author hsd77849
 */
public class balanceBean implements Serializable
{

    private String table;

    /**
     * Set table
     *
     * @param x string contains html code for the transaction table
     */
    public void setTable(String x)
    {
        table = x;
    }

    /**
     * gets the table
     *
     * @return a string representing the table(html code)
     */
    public String getTable()
    {
        return table;
    }

}
