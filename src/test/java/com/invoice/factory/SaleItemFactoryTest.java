package com.invoice.factory;

import com.invoice.entity.SaleItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SaleItemFactoryTest {

    @Test
    public void testCreateSaleItem() {
        SaleItem newSaleItem = new SaleItem(1L,10L, new BigDecimal(100));
        String expectedSaleItemRegister = String.format("%s-%s-%s",
                newSaleItem.getId().toString(),
                newSaleItem.getQuantity().toString(),
                newSaleItem.getUnitPrice().toString());

        IEntityFactory factory = new SaleItemFactory();
        SaleItem saleItem = (SaleItem)factory.create(expectedSaleItemRegister);

        Assert.assertNotNull(saleItem);
        Assert.assertEquals(saleItem.getId(), newSaleItem.getId());
        Assert.assertEquals(saleItem.getQuantity(), newSaleItem.getQuantity());
        Assert.assertEquals(saleItem.getUnitPrice(), newSaleItem.getUnitPrice());
    }
}
