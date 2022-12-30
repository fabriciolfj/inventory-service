package com.github.fabriciolfj.inventoryservice.usecase.domain;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderFindProductInventory;
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
    private final ProviderFindProductInventory providerFindProductInventory;

    public Mono<Void> execute(final InventoryEntity entity) {
        return Mono.just(entity)
                .doOnNext(c -> log.info("Inventory executed {}", c.getCode()))
                .flatMap(c -> providerFindProductInventory.process(c.getProduct()))
                .map(entity::updateBalance)
                .flatMap(providerSaveInventory::process)
                .switchIfEmpty(Mono.defer(() -> providerSaveInventory.process(entity.initBalance())))
                .doOnSuccess(c -> log.info("Inventory save success {}", c))
                .then();
    }
}
