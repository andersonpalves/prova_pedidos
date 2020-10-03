package com.invoice.factory;

import com.invoice.entity.Entity;
import com.invoice.entity.Sale;
import com.invoice.entity.SaleItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaleFactory implements IEntityFactory, ISaleFactory {

    @Override
    public Entity create(String register) {
        String[] arrayValues = register.split(ClientFactory.SEPARATOR_LETTER);
        return new Sale(new Long(arrayValues[1]), createSaleItems(arrayValues[2]), arrayValues[3]);
    }

    @Override
    public List<SaleItem> createSaleItems(String itemsRegister){
        List<SaleItem> saleItems = new ArrayList();
        List<String> items = Arrays.asList(itemsRegister.replace("[", "").replace("]", "").split(","));
        IEntityFactory saleItemsFactory = new SaleItemFactory();
        for(String item : items) {
            saleItems.add((SaleItem) saleItemsFactory.create(item));
        }
        return saleItems;
    }

}
