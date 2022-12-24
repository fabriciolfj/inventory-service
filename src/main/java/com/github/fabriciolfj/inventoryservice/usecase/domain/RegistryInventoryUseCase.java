package com.github.fabriciolfj.inventoryservice.usecase.domain;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderSaveInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistryInventoryUseCase {

    private final ProviderSaveInventory providerSaveInventory;

    public Mono<Void> execute(final Mono<InventoryEntity> entity) {
        return entity
                .doOnNext(c -> log.info("Inventory executed {}", c.code()))
                .flatMap(providerSaveInventory::process)
                .doOnSuccess(c -> log.info("Inventory save success"));
    }
}
