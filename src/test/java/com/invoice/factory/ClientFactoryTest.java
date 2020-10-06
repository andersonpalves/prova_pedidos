package com.invoice.factory;

import com.invoice.entity.Client;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class ClientFactoryTest {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ClientFactoryTest.class));

    @Before
    public void setup() {
        LOGGER.info("Iniciando a execução dos testes da Classe Client");
    }

    @After
    public void finish() {
        LOGGER.info("Iniciando a execução dos testes da Classe Client");
    }

    @Test
    public void testCreateClient() {
        Client newClient = new Client("2345675434544345","Jose da Silva","Rural");
        String expectedClientRegister = String.format("002ç%sç%sç%s", newClient.getCnpj(), newClient.getName(), newClient.getBusinessArea());

        IEntityFactory factory = new ClientFactory();
        Client client = (Client) factory.create(expectedClientRegister);

        Assert.assertNotNull(newClient);
        Assert.assertEquals(client.getCnpj(), newClient.getCnpj());
        Assert.assertEquals(client.getName(), newClient.getName());
        Assert.assertEquals(client.getBusinessArea(), newClient.getBusinessArea());
    }
}
