package com.github.fabriciolfj.inventoryservice.entrypoint.mappers;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.entities.TypeOperation;
import com.github.fabriciolfj.inventoryservice.entrypoint.dto.request.InventoryRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryDTOMapper {

    private InventoryDTOMapper(){ }

    public static InventoryEntity toEntity(final InventoryRequest request) {
        return InventoryEntity
                .builder()
                .quantity(request.getQuantity())
                .code(UUID.randomUUID().toString())
                .typeOperation(TypeOperation.valueOf(request.getOperation()))
                .product(request.getProduct())
                .registration(LocalDateTime.now())
                .build();
    }
}
