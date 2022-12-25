package com.github.fabriciolfj.inventoryservice.providers.database.mappers;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.entities.TypeOperation;
import com.github.fabriciolfj.inventoryservice.providers.database.data.InventoryData;

public class InventoryDataMapper {

    private static final int QUANTIY_ZERO = 0;
    private InventoryDataMapper() { }

    public static InventoryData toData(final InventoryEntity entity) {
        return InventoryData
                .builder()
                .exit(entity.isExit() ? entity.getQuantity() : QUANTIY_ZERO)
                .entrance(entity.isEntrance() ? entity.getQuantity() : QUANTIY_ZERO)
                .code(entity.getCode())
                .product(entity.getProduct())
                .balance(entity.getBalance())
                .build();
    }

    public static InventoryEntity toEntity(final InventoryData data) {
        return InventoryEntity.builder()
                .quantity(data.getEntrance() > 0? data.getEntrance() : data.getExit())
                .balance(data.getBalance())
                .registration(data.getDateRegistration())
                .code(data.getCode())
                .product(data.getProduct())
                .typeOperation(data.getEntrance() > 0? TypeOperation.ENTRANCE : TypeOperation.EXIT)
                .build();
    }
}
