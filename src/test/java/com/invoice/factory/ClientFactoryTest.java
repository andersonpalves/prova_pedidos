package com.invoice.factory;

import com.invoice.entity.Client;
import org.junit.Assert;
import org.junit.Test;

public class ClientFactoryTest {

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
