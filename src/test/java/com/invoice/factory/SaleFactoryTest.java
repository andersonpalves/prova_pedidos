package com.invoice.factory;

import com.invoice.entity.Sale;
import com.invoice.entity.SaleItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SaleFactoryTest {

    @Test
    public void testCreateSale() {
        Sale newSale = new Sale(10L, new ArrayList(), "Pedro");
        newSale.addItens(new SaleItem(1L, 10L, new BigDecimal(100)));
        newSale.addItens(new SaleItem(2L, 30L, new BigDecimal(2.50)));
        newSale.addItens(new SaleItem(3L, 40L, new BigDecimal(3.10)));

        String expectedSaleRegister = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        IEntityFactory factory = new SaleFactory();

        Sale sale = (Sale) factory.create(expectedSaleRegister);
        Assert.assertNotNull(sale);
        Assert.assertEquals(sale.getId(), newSale.getId());
        Assert.assertEquals(sale.getSalesmanName(), newSale.getSalesmanName());
        Assert.assertEquals(sale.getItems().size(), newSale.getItems().size());
    }
}