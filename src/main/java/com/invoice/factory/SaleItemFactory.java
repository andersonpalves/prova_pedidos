package com.invoice.factory;

import com.invoice.entity.Entity;
import com.invoice.entity.SaleItem;

import java.math.BigDecimal;

public class SaleItemFactory implements IEntityFactory {

    @Override
    public Entity create(String register) {
        String[] arrayValues = register.split(ClientFactory.SEPARATOR_HIFEN);
        return new SaleItem(new Long(arrayValues[0]), new Long(arrayValues[1]), new BigDecimal(arrayValues[2]));
    }
}
