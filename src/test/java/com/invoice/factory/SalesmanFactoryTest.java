package com.invoice.factory;

import com.invoice.entity.Salesman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class SalesmanFactoryTest {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ClientFactoryTest.class));

    @Before
    public void setup() {
        LOGGER.info("Iniciando a execução dos testes da Classe Salesman");
    }

    @After
    public void finish() {
        LOGGER.info("Iniciando a execução dos testes da Classe Salesman");
    }

    @Test
    public void testCreateSalesmanByString() {
        Salesman newSalesman = new Salesman("1234567891234", "Pedro",new BigDecimal(5000));
        String expectedSalesmanRegister = String.format("001ç%sç%sç%s",
                newSalesman.getCpf(),
                newSalesman.getName(),
                newSalesman.getSalary());

        IEntityFactory factory = new SalesmanFactory();
        Salesman salesman = (Salesman) factory.create(expectedSalesmanRegister);

        Assert.assertNotNull(salesman);
        Assert.assertEquals(salesman.getCpf(), newSalesman.getCpf());
        Assert.assertEquals(salesman.getName(), newSalesman.getName());
        Assert.assertEquals(salesman.getSalary(), newSalesman.getSalary());
    }
}
