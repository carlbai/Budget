package com.example.budget.app;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by carlbai on 9/11/14.
 */
public class Transaction implements Serializable
{
    String category;
    BigDecimal amount;

    public Transaction(String category, BigDecimal amount)
    {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory()
    {
        return category;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

}
