package com.github.fabriciolfj.inventoryservice.usecase;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import reactor.core.publisher.Mono;

public interface ProviderSaveInventory {

    Mono<InventoryEntity> process(final InventoryEntity entity);
}
