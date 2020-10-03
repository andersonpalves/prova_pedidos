package com.invoice.factory;

import com.invoice.entity.Client;
import com.invoice.entity.Entity;

public class ClientFactory implements IEntityFactory {

    public static final String SEPARATOR_LETTER = "ç";
    public static final String SEPARATOR_HIFEN = "-";

    @Override
    public Entity create(String register) {
        String[] arrayValues = register.split(SEPARATOR_LETTER);
        return new Client(arrayValues[1], arrayValues[2], arrayValues[3]);
    }

}
