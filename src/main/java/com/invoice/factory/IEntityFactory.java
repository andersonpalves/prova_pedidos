package com.invoice.factory;

import com.invoice.entity.Entity;

public interface IEntityFactory {
    Entity create(String register);
}
