package com.invoice.service;

import com.invoice.entity.*;
import com.invoice.factory.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterService {

    public static final String FORMAT_OUTPUT = "Total de Clientes: %d\n" +
            "Total de vendedores: %d\n" +
            "Id venda mais cara: %02d\n" +
            "Pior vendedor: %s";
    private List<Entity> allRegisters = new ArrayList<>();

    public void register(String line) {
        IEntityFactory factory = createFactory(line);
        allRegisters.add(factory.create(line));
    }

    public void registerAll(List<String> lines){
        lines.stream().forEach(this::register);
    }

    public List<Client> getAllClients() {
        return allRegisters.stream()
                .filter(reg -> reg instanceof Client)
                .map(reg -> (Client) reg)
                .collect(Collectors.toList());
    }

    public List<Salesman> getAllSalesmen() {
        return allRegisters.stream()
                .filter(reg -> reg instanceof Salesman)
                .map(reg -> (Salesman) reg)
                .collect(Collectors.toList());
    }

    public List<Sale> getAllSales() {
        return allRegisters.stream()
                .filter(reg -> reg instanceof Sale)
                .map(reg -> (Sale) reg)
                .collect(Collectors.toList());
    }

    public Long getMostExpensiveBySaleId() {
        BigDecimal mostExpensivePrice = BigDecimal.ZERO;
        Long mostExpensiveSaleId = 0L;
        List<Sale> sales = getAllSales();
        for (Sale sale : sales) {
            BigDecimal purchaseTotal = purchaseTotal(sale);
            if (mostExpensivePrice.compareTo(purchaseTotal) <= 0) {
                mostExpensiveSaleId = sale.getId();
                mostExpensivePrice = purchaseTotal;
            }
        }
        return mostExpensiveSaleId;
    }

    public String getWorstSalesman(){
        List<Sale> sales = getAllSales();
        BigDecimal worstSalePrice = purchaseTotal(sales.get(0));
        Sale worstSale = sales.get(0);

        for(Sale sale : sales) {
            if(worstSalePrice.compareTo(purchaseTotal(sale)) < 0){
            } else {
                worstSalePrice = purchaseTotal(sale);
                worstSale = sale;
            }
        }

        return worstSale.getSalesmanName();
    }

    private IEntityFactory createFactory(String line) {
        String factoryType = line.substring(0, 3);
        IEntityFactory factory;

        switch (factoryType) {
            case "001":
                factory = new SalesmanFactory();
                break;
            case "002":
                factory = new ClientFactory();
                break;
            case "003":
                factory = new SaleFactory();
                break;
            default:
                throw new InvalidParameterException("Factory does not implemented");
        }
        return factory;
    }

    private BigDecimal purchaseTotal(Sale sale) {
        BigDecimal purchaseTotal = BigDecimal.ZERO;
        for (SaleItem item : sale.getItems()) {
            purchaseTotal = purchaseTotal.add(item.getPrice());
        }
        return purchaseTotal;
    }

    public String getaAllDataReport() {
        return String.format(
            FORMAT_OUTPUT,
            getAllClients().size(),
            getAllSalesmen().size(),
            getMostExpensiveBySaleId(),
            getWorstSalesman()
        );
    }
}
