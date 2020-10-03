package com.invoice.factory;

import com.invoice.entity.SaleItem;

import java.util.List;

public interface ISaleFactory {
    List<SaleItem> createSaleItems(String register);
}
