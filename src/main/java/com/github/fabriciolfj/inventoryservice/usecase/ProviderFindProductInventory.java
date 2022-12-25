package com.github.fabriciolfj.inventoryservice.usecase;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import reactor.core.publisher.Mono;

public interface ProviderFindProductInventory {

    Mono<InventoryEntity> process(final String code);
}
