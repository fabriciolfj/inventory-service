package com.github.fabriciolfj.inventoryservice.providers.database.mappers;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.providers.database.data.InventoryData;

public class InventoryDataMapper {

    private static final int QUANTIY_ZERO = 0;
    private InventoryDataMapper() { }

    public static InventoryData toData(final InventoryEntity entity) {
        return InventoryData
                .builder()
                .exit(entity.isExit() ? entity.quantity() : QUANTIY_ZERO)
                .entrance(entity.isEntrance() ? entity.quantity() : QUANTIY_ZERO)
                .code(entity.code())
                .product(entity.product())
                .build();
    }
}
