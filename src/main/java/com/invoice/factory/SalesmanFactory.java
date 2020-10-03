package com.invoice.factory;

import com.invoice.entity.Entity;
import com.invoice.entity.Salesman;

import java.math.BigDecimal;

public class SalesmanFactory implements IEntityFactory {

    @Override
    public Entity create(String register) {
        String[] arrayValues = register.split(ClientFactory.SEPARATOR_LETTER);
        return new Salesman(arrayValues[1], arrayValues[2], new BigDecimal(arrayValues[3]));
    }

}